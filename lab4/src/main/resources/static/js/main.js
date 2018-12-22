var customActions = {
    update: {method: 'GET', url: '/point/update{/r}'}
};

var pointApi  = Vue.resource('/point{/id}',{},customActions);

Vue.component ('graph', {
    template:
        '<svg id="graph" width="340" height="340" @click="get_points">' +
        '<rect x="20" y="95" width="150" height="75"  fill="blue" />' +
        '<path d="M 245 170 A 75 75 0 0 0 170 95 L 170 170 Z" fill="blue" stroke="none" fill-rule="evenodd"/>' +
        '<polygon points="170,170 245,170 170,245" fill="blue"/>' +
        '<text id="graph_x" x="315" y="184" font-family="monospace" font-size="20" fill="white" stroke="black">R</text>' +
        '<text id="graph_y" y="25" x="174" font-family="monospace" font-size="20" fill="white" stroke="black">R</text>' +
        '<line x1="50%" y1="0%" x2="50%" y2="100%" style="stroke:rgb(0,0,0);stroke-width:2" />' +
        '<line x1="0%" y1="50%" x2="100%" y2="50%" style="stroke:rgb(0,0,0);stroke-width:2" />' +
        '</svg>',
    methods: {
        get_points: function () {
            document.getElementById("invalidR").textContent = "";
            document.getElementById("save").disabled = false;
            var pt = document.getElementById('graph').createSVGPoint();
            pt.x = event.clientX;
            pt.y = event.clientY;
            var r = document.getElementsByName("valueR")[8].checked ? 3 :
                document.getElementsByName("valueR")[7].checked ? 2 :
                    document.getElementsByName("valueR")[6].checked ? 1 : -1;
            var cursorpt =  pt.matrixTransform(document.getElementById('graph').getScreenCTM().inverse());
            if (r > 0) {
                var xVal = (cursorpt.x-170)*r/150;
                var yVal = -(cursorpt.y-170)*r/150;
                var point = {
                    point: {
                        x: xVal + "",
                        y: yVal + "",
                        r: r + ""
                    }
                };
                pointApi.save({}, point).then(result =>
                result.json().then(data => {
                    redraw();
            }),
                result=>{
                });
            } else {
                document.getElementById("invalidR").textContent = "Значение r должно быть положительным.";
                document.getElementById("save").disabled = true;
            }
        }
    }
});


Vue.component('point-form', {
    template:
        '<div>' +
        '<label>X</label>'+
        '<input class="w3-radio" type="radio" id="Xminus5" name="valueX" value="-5" v-model="xValue" />'+
        '<label for="Xminus5">-5</label>'+
        '<input class="w3-radio" type="radio" id="Xminus4" name="valueX" value="-4" v-model="xValue" />'+
        '<label for="Xminus4">-4</label>'+
        '<input class="w3-radio" type="radio" id="Xminus3" name="valueX" value="-3" v-model="xValue" />'+
        '<label for="Xminus3">-3</label>'+
        '<input class="w3-radio" type="radio" id="Xminus2" name="valueX" value="-2" v-model="xValue" />'+
        '<label for="Xminus2">-2</label>'+
        '<input class="w3-radio" type="radio" id="Xminus1" name="valueX" value="-1" v-model="xValue" />'+
        '<label for="Xminus1">-1</label>'+
        '<input class="w3-radio" type="radio" id="Xplus0" name="valueX" value="0" v-model="xValue" />'+
        '<label for="Xplus0">0</label>'+
        '<input class="w3-radio" type="radio" id="Xplus1" name="valueX" value="1" v-model="xValue" />'+
        '<label for="Xplus1">1</label>'+
        '<input class="w3-radio" type="radio" id="Xplus2" name="valueX" value="2" v-model="xValue" />'+
        '<label for="Xplus2">2</label>'+
        '<input class="w3-radio" type="radio" id="Xplus3" name="valueX" value="3" v-model="xValue" />'+
        '<label for="Xplus3">3</label>'+
        '<br/>'+
        '<label id="yLabel">Y</label>'+
        '<input type="text" id="yValue" placeholder="Write y" v-model="yValue" @input="validate_y" oninput="rpls(this)"/>' +
        '<label id="invalidY" style="color: red"/>' +
        '<br/>'+
        '<label id="rLabel" >R</label>'+
        '<input class="w3-radio" type="radio" id="Rminus5" name="valueR" value="-5" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rminus5">-5</label>'+
        '<input class="w3-radio" type="radio" id="Rminus4" name="valueR" value="-4" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rminus4">-4</label>'+
        '<input class="w3-radio" type="radio" id="Rminus3" name="valueR" value="-3" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rminus3">-3</label>'+
        '<input class="w3-radio" type="radio" id="Rminus2" name="valueR" value="-2" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rminus2">-2</label>'+
        '<input class="w3-radio" type="radio" id="Rminus1" name="valueR" value="-1" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rminus1">-1</label>'+
        '<input class="w3-radio" type="radio" id="Rplus0" name="valueR" value="0" v-model="rValue" @click="validate_r"/>'+
        '<label for="Rplus0">0</label>'+
        '<input class="w3-radio" type="radio" id="Rplus1" name="valueR" value="1" checked="true" v-model="rValue" @click="validate_r" oninput="redraw()"/>'+
        '<label for="Rplus1">1</label>'+
        '<input class="w3-radio" type="radio" id="Rplus2" name="valueR" value="2" v-model="rValue" @click="validate_r" oninput="redraw()"/>'+
        '<label for="Rplus2">2</label>'+
        '<input class="w3-radio" type="radio" id="Rplus3" name="valueR" value="3" v-model="rValue" @click="validate_r" oninput="redraw()"/>'+
        '<label for="Rplus3">3</label>'+
        '<label id="invalidR" style="color: red"/>' +
        '<br/>'+
        '<input type="button" id="save" value="Save" @click="save"/>' +
        '</div>',
    data: function() {
        return {
            xValue: 0,
            yValue: '',
            rValue: 0
        }
    },
    methods: {
        save: function() {
            document.getElementById("invalidY").textContent = "";
            document.getElementById("invalidR").textContent = "";
            document.getElementById("save").disabled = false;
            var error= false;
            if (document.getElementById("yValue").value == null){
                document.getElementById("invalidY").textContent = "Y должен быть числом от -5 до 3.";
                document.getElementById("save").disabled = true;
            }
            if (document.getElementById("yValue").value > 3 || document.getElementById("yValue").value < -5) {
                error = true;
                document.getElementById("invalidY").textContent = "Y должен быть числом от -5 до 3.";
                document.getElementById("save").disabled = true;
            }
            var r = document.getElementsByName("valueR")[8].checked ? 3 :
                document.getElementsByName("valueR")[7].checked ? 2 :
                    document.getElementsByName("valueR")[6].checked ? 1 : -1;
            if (r <= 0) {
                error=true;
                document.getElementById("invalidR").textContent = "Значение r должно быть положительным.";
                document.getElementById("save").disabled = true;
            }
            if(error) return;
            var point = {x: this.xValue,
                         y: this.yValue,
                         r: this.rValue};
            pointApi.save({}, point).then(result =>
            result.json().then(data => {
                redraw();
            }),
            result=>{
            });
        },

        validate_r: function() {
            try {
                document.getElementById("invalidR").textContent = "";
                document.getElementById("save").disabled = false;
                var r = document.getElementsByName("valueR")[8].checked ? 3 :
                    document.getElementsByName("valueR")[7].checked ? 2 :
                        document.getElementsByName("valueR")[6].checked ? 1 : -1;
                if (r != parseFloat(r)) throw "R должен быть числом.";
                if (r <= 0) {
                    throw "Значение r должно быть положительным.";
                }
            } catch (e) {
                document.getElementById("invalidR").textContent = e;
                document.getElementById("save").disabled = true;
            }
        },

        validate_y: function(){
            document.getElementById("invalidY").textContent = "";
            document.getElementById("save").disabled = false;
            try {
                var y = document.getElementById("yValue").value;
                y = y.replace(",", ".");
                if (y != parseFloat(y)) throw "Y должен быть числом.";
                y = parseFloat(y);
                if (y > 3 || y < -5) {
                    throw "Значение Y должно находится между -5 и 3.";
                }
                return true;
            } catch (e) {
                document.getElementById("invalidY").textContent = e;
                document.getElementById("save").disabled = true;
                return false;
            }
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<graph />' +
        '<point-form />' +
        '</div>'
});


function rpls(elem) {
    elem.value = elem.value.replace(/[^\d,.-]/g, '');
}

function redraw() {
    console.log("4");
    var r = document.getElementsByName("valueR")[8].checked ? 3 :
        document.getElementsByName("valueR")[7].checked ? 2 :
            document.getElementsByName("valueR")[6].checked ? 1 : -1;
    document.getElementById("graph_x").textContent = r;
    document.getElementById("graph_y").textContent = r;
        pointApi.update({r:r}).then(result =>
        result.json().then(data => {
           var points = data;
            for (i = document.getElementsByClassName("point").length - 1; i >= 0;--i)
                document.getElementsByClassName("point")[0].remove();
            for (var i = 0; i < points.length; i++) {
                //точка
                var c = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
                var cx = (points[i].x * 150) / r + 170;
                c.setAttribute('cx',cx);
                c.setAttribute('class','point');
                var cy = 170 - points[i].y * 150 / r;
                c.setAttribute('cy',cy);
                c.setAttribute('r','3');
                c.setAttribute('stroke', 'black');
                if (points[i].hit) {
                    c.style.fill='green';
                } else {
                    c.style.fill='red';
                }
                document.getElementById('graph').appendChild(c);
            }
        }),
        result=>{
        });

}