/**
 * Created by ole-magnus on 3/15/15.
 */

$(document).ready(function(){
    var curView     = 0;
    if(window.sessionStorage.getItem('curView') !== undefined) curView = window.sessionStorage.getItem('curView');
    else window.sessionStorage.setItem('curView', 0);
    var slides      = [];
    var tags        = [];
    var curSlide    = 0;
    var socketUrl   = 'localhost';
    //var socket = new WebSocket(socketUrl);

    themeCSS.getInputField().setAttribute('id', 'themeCssEditor');
    $('#mainNav').children().eq(curView).show();
    changeView();
    $('#slideImageError').hide();
    $('#slideImagePreview').hide();
    $('#mainNav').children().each(function (i){
        $(this).on('click', function (){
            if(i == curView) return;
            $('#mainNav').children().each(function () {
                if($(this).hasClass("active")) $(this).removeClass("active");
            });
            $('#mainNav').children().eq(i).addClass("active");
            curView = i;
            window.sessionStorage.setItem('curView', i);
            changeView();
        });
    });

    $('.form-datetime').datetimepicker({
        format: "dd-mm-yyyy hh-ii"
    });


    $('#slideAdd').on('click', function () {
        var jsonText = '{ "type": "addSlide"}';
        getSlideList();
    });

    $('#slideDelete').on('click', function(){
        var jsonText = '{ "type": "removeSlide", "id": "' + curSlide + '" }';
        var json = JSON.parse(jsonText);
        //socket.send(json);
    });

    $('#slideSubmit').on('click', function(){
        var jsonText = '{ "type": "slide",' +
            '"id": "' + curSlide + '",' +
            '"title": "' + $('#slideName').val() + '",' +
            '"text": "' + getTextWithBreaks() + '", ' +
            '"picture": "' + $('img#slideImagePreview').attr('src') + '", ' +
            '"theme": "' + $("#slideSelectTheme").val() + '",' +
            '"tags": ' + getTags() + '}';
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
            if(!$(this).attr('disabled')) tags += '"' + this.value + '",';
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
            if(selectedTags.contains(this))$('#slideSelectTagsTo').append('<option value="' + this + '">' + this + '</option>');
            else $('#slideSelectTagsFrom').append('<option value="' + this + '">' + this + '</option>');
        });
    }

    function loadSlide(id){
        if(id > slides.length - 1) return;
        curSlide = id;
        var slide = slides[id];

        $('#slideTitle').val(slide.title);
        $('#slideText').val(getTextWithLineBreaks(slide.text));
        if(obj.image != "") {
            $('#slideImagePreview').attr('src', slide.image);
            $('#slideImagePreview').show();
            $('#slideImageError').hide();
        }
        else{
            $('#slideImagePreview').attr('src', "");
            $('#slideImagePreview').hide();
            $('#slideImageError').hide();
        }
        updateSlideTags(obj.tags);
        $('#slideSelectTheme').select(slide.theme);
    }

    function getSlideList(jsonText){
        var obj = JSON.parse(jsonText);
        slides = obj.slides;
        $('#slideList').html("");
        $(slides).each(function () {
            var opt = '<option value="' + this.id + '">'+
                    '<img src="' + this.image + '" class="listImage img-thumbnail" />' +
                    this.title +
                    '</option>';
            $('#slideList').find('ul').first().append(opt);
        });
    }

    function changeView(){
        $('#pageContent').children().hide();
        $('#pageContent-' + curView).show();
    }
});

$(document).on('refresh')