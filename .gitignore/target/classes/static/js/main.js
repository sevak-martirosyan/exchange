var nominal     = $(".nominal");
var currYen     = $(".currentYen");
var value =       $(".value");

var htmlString = 'Текущий курс:'+ '<br>';
$(document).ready(function() {
$.get('/getLast', function (data) {

  value.append(data.value);
    htmlString += value.prop("outerHTML");
    currYen.html(htmlString);
})});

function buy() {

    var sum= $('#buyInput').val();
    console.log(sum);
    $.get('/buy?sum='+sum, function (data) {
        console.log(data);
        $('#buyResult').html('вы купили: '+data.data+'  йен' +'  status: '+data.status)
    });
}
function sell() {
    var sum=$('#sellInput').val();
   console.log(sum);
    $.get('/sell?sum='+sum, function (data) {
        $('#sellResult').html('вы получили: '+data.data +'  рублей'+'  status: '+data.status)
    });
}