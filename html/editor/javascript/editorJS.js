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
    var themes      = [];
    var curDays     = [];
    var curSlide    = -2;
    var curTag      = -2;
    var curTheme    = -2;
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
    $('#addTag').removeAttr('disabled');
    $('#addTheme').removeAttr('disabled');

    /**
     * Set onclick response for each tab
     */
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

    /**
     * Change the view of the window
     * @param i index of the selected view
     */
    function changeView(i){
        $('#pageContent').children().hide();
        $('#pageContent-' + i).show();
    }

    // --- SLIDES ---

    /**
     * Create a new slide
     */
    $('#addSlide').on('click', function () {
        var slide = {
            type: 'slide',
            id: -1,
            title: getTranslation('new slide'),
            text: '',
            picture: '',
            theme: 0,
            tags: []
        };
        slides.push(slide);
        updateSlideList();
        $('#addSlide').attr('disabled', 'disabled');
        $('#slideList').find('ul.list-group').children().each(function(i){
            if($(this).data("id") == "-1"){
                $(this).trigger('click');
                return false;
            }
        });
    });

    /**
     * Remove the current slide
     */
    $('#slideDelete').on('click', function(){
        var json = {
            'type': 'removeSlide',
            'id': curSlide
        };
        // TODO Send json to server
    });

    /**
     * Submit the slide to the server
     */
    $('#slideSubmit').on('click', function(){
        var json = { "type": "slide",
            "id": curSlide,
            "title": $('#slideName').val(),
            "text": getTextWithBreaks(),
            "picture": $('img#slideImagePreview').attr('src'),
            "theme": parseInt($("#slideSelectTheme").val()),
            "tags": getTags()};
        var jsonText = JSON.stringify(json);
        console.log(jsonText);
    });

    /**
     * Reset the slide to the previously saved state
     */
    $('slideCancel').on('click', loadSlide(curSlide));

    /**
     * Replace line breaks (\n) in the slide text with html line breaks (<br />
     * @returns {XML|string|void} The new text
     */
    function getTextWithBreaks(){
        var text = $('#slideText').val();
        return text.replace('\n', '<br />');
    }

    /**
     * Replace html line breaks (<br /> with line breaks (\n)
     * @param text The text to change
     * @returns {XML|string|void}
     */
    function getTextWithLineBreaks(text){
        return text.replace(/<br.*>/, '\n');
    }

    /**
     * Get json array of the tags for the selected slide
     * @returns {Array}
     */
    function getTags(){
        var tags = [];
        $('#slideSelectTagsTo').children().each(function(){
            if(!$(this).attr('disabled')) tags.append($(this).attr('data-id'));
        });
        return tags;
    }

    /**
     * Get the image chosen, check that it is an image and display it, or an error if it isn't an image
     */
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

    /**
     * Add tag to slide
     */
    $('#slideSelectAdd').on('click', function(e){
        e.preventDefault();
        $('#slideSelectTagsFrom option:selected').each( function() {
            $('#slideSelectTagsTo').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
            $(this).remove();
        });
    });

    /**
     * Remove tag from slide
     */
    $('#slideSelectRemove').on('click', function(e){
        e.preventDefault();
        $('#slideSelectTagsTo option:selected').each( function() {
            $('#slideSelectTagsFrom').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
            $(this).remove();
        });
    });

    /**
     * Update the GUI-list of tags for the selected slide
     * @param selectedTags The tags belonging to the current slide
     */
    function updateSlideTags(selectedTags){
        $('#slideSelectTagsFrom').html("");
        $('#slideSelectTagsTo').html("");
        $(tags).each(function () {
            if(selectedTags.contains($(this).id)) $('#slideSelectTagsTo').append('<ul data-id="' + $(this).id + '">' + $(this).name + '</li>');
            else $('#slideSelectTagsFrom').append('<li data-id="' + $(this).id + '">' + $(this).name + '</option>');
        });
    }

    /**
     * Load a slide into the editor-view
     * @param id The ID of the tag
     */
    function loadSlide(id){
        var slide = null;
        $(slides).each(function () {
            if(this.id == id){
                slide = this;
                return false;
            }
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

    /**
     * Get the slide list from the json object received from the server
     * @param json The json object containing the slides
     */
    function getSlideList(json){
        slides = json.slides;
        updateSlideList();
        $('#addSlide').removeAttr('disabled');
    }

    /**
     * Add all slides to the slide list
     */
    function updateSlideList(){
        $('#slideList').find('ul').first().html("");
        $(slides).each(function () {
            var opt = '<li class="list-group-item" data-id="' + this.id + '">' + this.title + '</li>';
            $('#slideList').find('ul').first().append(opt);
        });
        addSlideListListener();
    }

    /**
     * Add onClick listener for the elements in the slide list. Sets the selected slide active
     */
    function addSlideListListener(){
        $('#slideList').find('ul').first().children().each(function(){
            $(this).on('click', function () {
                $('#slideList').find('ul').first().children().removeClass('active');
                loadSlide($(this).attr('data-id'));
                $(this).addClass('active');
            });
        });
    }

    // --- TAGS ---

    /**
     * Add tag to the tag list
     */
    $('#addTag').on('click', function(){
        var tag = {
            id: -1,
            title: 'new tag',
            startDate: '',
            endDate: '',
            days: [],
            repeat: 0,
            overwrite: 0
        };
        tags.push(tag);
        updateTagList();
        $('#addTag').attr('disabled', 'disabled');
        $('#tagList').find('ul.list-group').children().each(function(i){
            if($(this).data("id") == -1){
                $(this).trigger('click');
                return false;
            }
        });
    });

    /**
     * Update the GUI tag list with all the tags
     */
    function updateTagList(){
        $('#tagList').find('ul').first().html("");
        $(tags).each(function () {
            var opt = '<li class="list-group-item" data-id="' + this.id + '">' + this.title + '</li>';
            $('#tagList').find('ul').first().append(opt);
        });
        addTagListListener();
    }

    /**
     * Add listener to the tag list items in the tag list
     */
    function addTagListListener(){
        $('#tagList').find('ul').first().children().each(function(){
            $(this).on('click', function () {
                $('#tagList').find('ul').first().children().removeClass('active');
                loadTag(parseInt($(this).attr('data-id')));
                $(this).addClass('active');
            });
        });
    }

    /**
     * Load the tag into the tag editor view
     * @param id ID of the tag to be edited
     */
    function loadTag(id){
        var tag = null;
        $(tags).each(function () {
            if(this.id == id){
                tag = this;
                return false;
            }
        });
        if(tag == null) return;
        curTag = id;
        curDays = tag.days.slice(0);

        $('#tagName').val(tag.title);
        $('#tagDateFrom').val(tag.startDate);
        $('#tagDateTo').val(tag.endDate);
        updateDayList();
        $('#tagRepeat').val(tag.repeat);
        $('#tagOverwrite').val(tag.overwrite);
    }

    /**
     * Submit the tag to the server
     */
    $('#tagSubmit').on('click', function () {
        for(var tag in tags){
            if(tags[tag].id == curTag){
                tags[tag].days = curDays.slice(0);
                break;
            }
        }
        var json = {
            type: 'tag',
            id: curTag,
            title: $('#tagName').val(),
            startDate: $('#tagDateFrom').val(),
            endDate: $('#tagDateTo').val(),
            days: curDays,
            repeat: parseInt($('#tagRepeat').val()),
            overwrite: parseInt($('#tagOverride').val())
        };
        var jsonText = JSON.stringify(json);
        console.log(jsonText);
    });

    /**
     * Reset the tag to the last saved state
     */
    $('#tagCancel').on('click', function () {
        loadTag(curTag);
    });

    /**
     * Remove tag from the tag list
     */
    $('#tagDelete').on('click', function(){
        var json = {
            'type': 'removeTag',
            'id': curTag
        };
        //TODO send json to server
    });

    /**
     * Clear the modal for adding a day, i.e. set all the dropdowns to selection 0
     */
    function clearDaysModal(){
        $('#addDayModal').find('select').each(function(){
            $(this).children().removeAttr('selected');
            $(this).children().find('option').eq(0).attr('selected', 'selected');
        });
    }

    /**
     * Create a new json object containing information about the day
     * @param day
     * @param startHour
     * @param startMins
     * @param endHour
     * @param endMins
     * @returns {{day: *, startTime: string, endTime: string}}
     */
    function createDay(day, startHour, startMins, endHour, endMins){
        return {
            day: day,
            startTime: startHour + ":" + startMins,
            endTime: endHour + ":" + endMins
        };
    }

    /**
     * Add all days for the tag to the day list, and add event listeners for removing them
     */
    function updateDayList(){
        $('#dayList').find('li.liDay').remove();
        var i = 0;
        curDays.forEach(function(entry){
            $('#dayList').append('<li class="list-group-item floatContainer liDay" id="tagDayList-' + i + '">' + getTranslation(days[entry.day]) + ' | ' + entry.startTime + ' - ' + entry.endTime + '<button type="button" class="btn btn-default btnRight removeDay">&times;</button></li>');
            i++;
        });
        $('.removeDay').on('click', function () {
            var id = $(this).parent().attr('id').split('-')[-1];
            curDays.splice(id, 1);
            updateDayList();
        });
    }

    /**
     * Close the day modal
     */
    $('.daysModalClose').on('click', function () {
        clearDaysModal();
    });

    /**
     * Add the day to the current tag
     */
    $('#daysModalAdd').on('click', function () {
        var add = true;
        var day = createDay($('#days').val(), $("#hoursFrom").val(), $('#minsFrom').val(), $('#hoursTo').val(), $('#minsTo').val());
        curDays.forEach(function(entry){
            if(entry.startTime == day.startTime && entry.endTime == day.endTime && entry.day == day.day) add = false;
        });
        if(Date.parse('01/01/2000 ' + day.startTime) > Date.parse('01/01/2000 ' + day.endTime)) add = false;
        if(add) curDays.push(day);
        updateDayList();
        $('#addDayModal').modal('hide');
        clearDaysModal();
    });

    // --- THEMES ---

    /**
     * Add theme to the theme list and select it
     */
    $('#addTheme').on('click', function () {
        var theme = {
            id: -1,
            title: getTranslation('new theme'),
            css: '',
            description: ''
        };
        themes.push(theme);
        updateThemeList();
        $('#addTheme').attr('disabled', 'disabled');
        $('#themeList').find('ul.list-group').children().each(function(){
            if($(this).data('id') == -1){
                $(this).trigger('click');
                return false;
            }
        })
    });

    function updateThemeList(){
        $('#themeList').find('ul').first().html("");
        $(themes).each(function () {
            var opt = '<li class="list-group-item" data-id="' + this.id + '">' + this.title + '</li>';
            $('#themeList').find('ul').first().append(opt);
        });
        addThemeListListener();
    }

    function addThemeListListener(){
        $('#themeList').find('ul').first().children().each(function(){
            $(this).on('click', function () {
                $('#themeList').find('ul').first().children().removeClass('active');
                loadTheme(parseInt($(this).attr('data-id')));
                $(this).addClass('active');
            });
        });
    }

    function loadTheme(id){
        var theme = null;
        $(themes).each(function () {
            if(this.id == id){
                theme = this;
                return false;
            }
        });
        if(theme == null) return;
        curTheme = id;

        $('#themeName').val(theme.title);
        $('#themeCSS').val(theme.css);
        $('#themeDescription').val(theme.description);
    }

    $('#themeSubmit').on('click', function () {
        var json = {
            type: 'theme',
            id: curTheme,
            title: $('#themeTitle').val(),
            css: $('#themeCSS').val(),
            description: $('#themeDescription').val()
        };
        var jsonText = JSON.stringify(json);
        console.log(jsonText);
    });

    $('#themeCancel').on('click', function () {
        loadTheme(curTheme);
    });

    $('#themeDelete').on('click', function () {
        var json = {
            type: 'removeTheme',
            id: curTheme
        };
    });
});
