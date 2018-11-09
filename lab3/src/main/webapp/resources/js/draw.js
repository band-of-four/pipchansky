var pt;

var points = []; //пока пусть сюда сохраняется

document.addEventListener('DOMContentLoaded', function(){
    pt = document.getElementById('batman').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('batman').getScreenCTM().inverse());
    var r = document.getElementById('mainForm:r').value;
    points.push([(x+cursorpt.x - 227) * r /217, (y+cursorpt.y - 103) * r / 186]);
    redraw();
}

function addPointFromForm() {
    var r = document.getElementById('mainForm:r').value;
    var x = getX();
    var y = document.getElementById('mainForm:y').value;
    //TODO обработать числа с запятой
    points.push([x, y]);
    redraw();
}

function redraw(){
    var r = document.getElementById('mainForm:r').value;
    document.getElementById("batman_x").innerHTML = r;
    document.getElementById("batman_y").innerHTML = r/2;
    $(".point").remove();
    console.log(points);
    for (var i=0; i<points.length; i++) {
        var cx = 217 / r * points[i][0] + 227;
        var cy = 186 / r * points[i][1] + 103;
        var c = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
        c.setAttribute('cx', cx);
        c.setAttribute('class', 'point');
        c.setAttribute('cy', cy);
        c.setAttribute('r', '3');
        c.setAttribute('stroke', 'black');
        if (true) {
            c.style.fill = 'green';
        } else {
            c.style.fill = 'red';
        }
        document.getElementById('batman').appendChild(c);
    }
}

function getX() {
    if (document.getElementById('mainForm:x:0').checked) return -4;
    if (document.getElementById('mainForm:x:1').checked) return -3;
    if (document.getElementById('mainForm:x:2').checked) return -2;
    if (document.getElementById('mainForm:x:3').checked) return -1;
    if (document.getElementById('mainForm:x:4').checked) return 0;
    if (document.getElementById('mainForm:x:5').checked) return 1;
    if (document.getElementById('mainForm:x:6').checked) return 2;
    if (document.getElementById('mainForm:x:7').checked) return 3;
    if (document.getElementById('mainForm:x:8').checked) return 4;
}