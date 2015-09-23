$(document).ready(function () {
    if(!('WebSocket' in window)){
        return;
    }
    var socketURL = 'localhost';
    var socket = new WebSocket(socketURL);

    socket.onmessage = function(msg){
        msg = JSON.parse(msg);
        if(msg.type != 'slide') return;
        $('#style').html(msg.style);
        $('#title').text(msg.title);
        $('#img').attr('src', msg.picture);
        $('#text').html(msg.text);
    };
});