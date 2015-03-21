/**
 * Created by ole-magnus on 3/15/15.
 */

$(document).ready(function(){
    var curView     = 0;
    var sessionCurView = window.sessionStorage.getItem('curView');
    if(sessionCurView !== undefined && sessionCurView !== null) curView = window.sessionStorage.getItem('curView');
    else window.sessionStorage.setItem('curView', 0);
    var slides      = [];
    var tags        = [];
    var curSlide    = 0;
    var curTag      = 0;
    var days        = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'];
    var socketUrl   = 'localhost';
    var calFrom     = new dhtmlXCalendarObject("tagDateFrom", false);
    var calTo       = new dhtmlxCalendarObject("tagDateTo", false);
    var emptySlide  = {
        'type': 'slide',
        'title': '',
        'text': '',
        'picture': '',
        'tags': [],
        'theme': 0
    };
    //var socket = new WebSocket(socketUrl);

    calFrom.setSensitiveRange(null, calTo.getDate());
    calTo.setSensitiveRange(calFrom.getDate(), null);
    calFrom.attachEvent('onClick', function(date){
        calTo.clearSensitiveRange();
        calTo.setSensitiveRange(date, null);
    });
    calTo.attachEvent('onClick', function(date){
        calFrom.clearSensitiveRange();
        calFrom.setSensitiveRange(null, date);
    });

    $('#mainNav').children().eq(curView).addClass('active');
    $('#pageContent').children().hide();
    $('div#pageContent-' + curView).show();
    $('#slideImageError').hide();
    $('#slideImagePreview').hide();
    $('#addSlide').removeAttr('disabled');


    $('#mainNav').children().each(function (i){
        $(this).on('click', function (){
            if(i == curView) return;
            $('#mainNav').children().each(function () {
                if($(this).hasClass("active")) $(this).removeClass("active");
            });
            $('#mainNav').children().eq(i).addClass("active");
            curView = i;
            window.sessionStorage.setItem('curView', i);
            changeView(i);
        });
    });

    $('#addSlide').on('click', function () {
        var slide = {'type': slide,
            'id': -1,
            'title': getTranslation('new slide'),
            'text': '',
            'picture': '',
            'theme': 0,
            'tags': []
        };
        slides.push(slide);
        updateSlideList();
        $('#addSlide').attr('disabled', 'disabled');
    });

    $('#slideDelete').on('click', function(){
        var jsonText = '{ "type": "removeSlide", "id": "' + curSlide + '" }';
        //socket.send(json);
    });

    $('#slideSubmit').on('click', function(){
        var json = { "type": "slide",
            "id": curSlide,
            "title": $('#slideName').val(),
            "text": getTextWithBreaks(),
            "picture": $('img#slideImagePreview').attr('src'),
            "theme": $("#slideSelectTheme").val(),
            "tags": getTags()};
        var jsonText = JSON.stringify(json);
        console.log(jsonText);
    });

    $('slideCancel').on('click', loadSlide(curSlide));

    function getTextWithBreaks(){
        var text = $('#slideText').val();
        text.replace('\n', '<br />');
        return text;
    }

    function getTextWithLineBreaks(text){
        return text.replace(/<br.*>/, '\n');
    }

    function getTags(){
        var tags = "[";
        $('#slideSelectTagsTo').children().each(function(){
            if(!$(this).attr('disabled')) tags += '"' + $(this).attr('data-id') + '",';
        });
        tags = tags.substr(0, tags.length - 1);
        tags += "]";
        return tags;
    }

    $('#slideImageChooser').on('change', function (e) {
        var file = e.originalEvent.target.files[0], reader = new FileReader();
        reader.onload = function(evt){
            $('#slideImageError').hide();
            $('#slideImagePreview').attr('src', evt.target.result);
            $('#slideImagePreview').show();
        }
        if(file.type.match('image.*')){
            reader.readAsDataURL(file);
        }
        else{
            $('#slideImagePreview').attr('src', "");
            $('#slideImagePreview').hide();
            $('#slideImageError').show();
        }
    });

    $('#slideSelectAdd').on('click', function(e){
        e.preventDefault();
        $('#slideSelectTagsFrom option:selected').each( function() {
            $('#slideSelectTagsTo').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
            $(this).remove();
        });
    });
    $('#slideSelectRemove').on('click', function(e){
        e.preventDefault();
        $('#slideSelectTagsTo option:selected').each( function() {
            $('#slideSelectTagsFrom').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
            $(this).remove();
        });
    });

    function updateSlideTags(selectedTags){
        $('#slideSelectTagsFrom').html("");
        $('#slideSelectTagsTo').html("");
        $(tags).each(function () {
            if(selectedTags.contains(this.id))$('#slideSelectTagsTo').append('<ul data-id="' + this.id + '">' + this.name + '</li>');
            else $('#slideSelectTagsFrom').append('<li data-id="' + this.id + '">' + this.name + '</option>');
        });
    }

    function loadSlide(id){
        var slide;
        $(slides).each(function () {
            if(this.id == id) slide = this;
        });
        if(slide == null) return;
        curSlide = id;

        $('#slideTitle').val(slide.title);
        $('#slideText').val(getTextWithLineBreaks(slide.text));
        if(slide.picture != "") {
            $('#slideImagePreview').attr('src', slide.image);
            $('#slideImagePreview').show();
            $('#slideImageError').hide();
        }
        else{
            $('#slideImagePreview').attr('src', "");
            $('#slideImagePreview').hide();
            $('#slideImageError').hide();
        }
        updateSlideTags(slide.tags);
        $('#slideSelectTheme').select(slide.theme);
    }

    function getSlideList(json){
        slides = json.slides;
        updateSlideList();
        $('#addSlide').removeAttr('disabled');
    }

    function updateSlideList(){
        $('#slideList').find('ul').first().html("");
        $(slides).each(function () {
            var opt = '<li class="list-group-item" data-id="' + this.id + '">' + this.title + '</li>';
            $('#slideList').find('ul').first().append(opt);
        });
        addSlideListListener();
    }

    function addSlideListListener(){
        $('#slideList').find('ul').first().children().each(function(){
            $(this).on('click', function () {
                $('#slideList').find('ul').first().children().removeClass('active');
                loadSlide($(this).attr('data-id'));
                $(this).addClass('active');
            });
        });
    }

    function changeView(i){
        $('#pageContent').children().hide();
        $('#pageContent-' + i).show();
    }

    function clearDaysModal(){
        $('#addDayModal').find('select').each(function(){
            $(this).children().removeAttr('selected');
            $(this).children().find('option').eq(0).attr('selected', 'selected');
        });
    }

    function createDay(day, startHour, startMins, endHour, endMins){
        return {'day': day, 'startTime':startHour + ":" + startMins, 'endTime':endHour + ":" + endMins};
    }

    function updateDayList(){
        var tag;
        tags.forEach(function(entry){
            if(entry.id = curTag) tag = entry;
        });
        $('#dayList').html('<li class="list-group-item list-group-item-info floatContainer"><strong>' + getTranslation('days') + '</strong><button type="button" class="btn btn-default btnRight" data-toggle="modal" data-target="#addDayModal">' + getTranslation('add day') + '</button></li>');
        var i = 0;
        tag.days.forEach(function(entry){
            $('#dayList').append('<li class="list-group-item floatContainer" id="tagDayList-' + i + '">' + getTranslation(days[entry.day]) + ' | ' + entry.startTime + ' - ' + entry.endTime + '<button type="button" class="btn btn-default btnRight removeDay">&times;</button></li>');
        });
        $('.removeDay').on('click', function () {
            var id = $(this).parent().attr('id').split('-')[-1];
            tag.days.splice(id, 1);
            updateDayList();
        });
    }

    $('.daysModalClose').on('click', function () {
        clearDaysModal();
    });

    $('#daysModalAdd').on('click', function () {
        var tag;
        tags.forEach(function(entry){
            if(entry.id = curTag) tag = entry;
        });
        var add = true;
        if(tag == null || tag == undefined) tag = {};
        if(tag.days == null || tag.days == undefined) tag.days = [];
        var day = createDay($('#days').val(), $("#hoursFrom").val(), $('#minsFrom').val(), $('#hoursTo').val(), $('#minsTo').val());
        tag.days.forEach(function(entry){
            if(entry.startTime == day.startTime && entry.endTime == day.endTime && entry.day == day.day) add = false;
        });
        if(Date.parse('01/01/2000 ' + day.startTime) > Date.parse('01/01/2000 ' + day.endTime)) add = false;
        if(add) tag.days.push(day);
        updateDayList();
        $('#addDayModal').modal('hide');
        clearDaysModal();
    });
});
