var pt;

document.addEventListener('DOMContentLoaded', function(){
pt = document.getElementById('batman').createSVGPoint();

});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('batman').getScreenCTM().inverse());

    //сохранить точку
    redraw(x+cursorpt.x, y+cursorpt.y, 0);
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
   // console.log(document.getElementById("batman"));
    document.getElementById('batman').appendChild(c);
}