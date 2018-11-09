var pt;

document.addEventListener('DOMContentLoaded', function(){
    pt = document.getElementById('batman').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('batman').getScreenCTM().inverse());
    var r = document.getElementById('mainForm:r').value;
    document.getElementById("batman_x").innerHTML = r;
    document.getElementById("batman_y").innerHTML = r/2;
    //TODO сохранить точку
    redraw(x+cursorpt.x, y+cursorpt.y, r);
}

function addPointFromForm() {
    var r = document.getElementById('mainForm:r').value;
    var x = getX();
    var y = document.getElementById('mainForm:y').value;
    document.getElementById("batman_x").innerHTML = r;
    document.getElementById("batman_y").innerHTML = r/2;
    //TODO обработать числа с запятой
    //TODO сохранить точку
    x = 217 / r * x + 227;
    y = 186 / r * y + 103;
    redraw(x, y, r)
}

function redraw(elemX, elemY, elemR){
    var c = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
    c.setAttribute('cx', elemX);
    c.setAttribute('class','point');
    c.setAttribute('cy',elemY);
    c.setAttribute('r','3');
    c.setAttribute('stroke', 'black');
    if (true) {
        c.style.fill='green';
    } else {
        c.style.fill='red';
    }
    document.getElementById('batman').appendChild(c);
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