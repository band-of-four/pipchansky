var pt;

document.addEventListener('DOMContentLoaded', function(){
    pt = document.getElementById('batman').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('batman').getScreenCTM().inverse());
    var r = document.getElementById('mainForm:r').value;
    document.getElementById("mainForm:hiddenX").value = (x+cursorpt.x - 227) * r /217;
    document.getElementById("mainForm:hiddenY").value = -(y+cursorpt.y - 103) * r / 186;
    document.getElementById("mainForm:areaClickBtn").click();
}

