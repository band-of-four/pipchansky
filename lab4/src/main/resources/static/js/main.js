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
        '<input type="text" id="yValue" placeholder="Write y" v-model="yValue" />' +
        '<br/>'+
        '<label id="rLabel" >R</label>'+
        '<input class="w3-radio" type="radio" id="Rminus5" name="valueR" value="-5" v-model="rValue" />'+
        '<label for="Rminus5">-5</label>'+
        '<input class="w3-radio" type="radio" id="Rminus4" name="valueR" value="-4" v-model="rValue" />'+
        '<label for="Rminus4">-4</label>'+
        '<input class="w3-radio" type="radio" id="Rminus3" name="valueR" value="-3" v-model="rValue" />'+
        '<label for="Rminus3">-3</label>'+
        '<input class="w3-radio" type="radio" id="Rminus2" name="valueR" value="-2" v-model="rValue" />'+
        '<label for="Rminus2">-2</label>'+
        '<input class="w3-radio" type="radio" id="Rminus1" name="valueR" value="-1" v-model="rValue" />'+
        '<label for="Rminus1">-1</label>'+
        '<input class="w3-radio" type="radio" id="Rplus0" name="valueR" value="0" v-model="rValue" />'+
        '<label for="Rplus0">0</label>'+
        '<input class="w3-radio" type="radio" id="Rplus1" name="valueR" value="1" v-model="rValue" />'+
        '<label for="Rplus1">1</label>'+
        '<input class="w3-radio" type="radio" id="Rplus2" name="valueR" value="2" v-model="rValue" />'+
        '<label for="Rplus2">2</label>'+
        '<input class="w3-radio" type="radio" id="Rplus3" name="valueR" value="3" v-model="rValue" />'+
        '<label for="Rplus3">3</label>'+
        '<br/>'+
        '<input type="button" id="save" value="Save" @click="save" />' +
        '<br/>'+
        '<div>X: {{ xValue }}</div>'+
        '<div>Y: {{ yValue }}</div>'+
        '<div>R: {{ rValue }}</div>'+
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
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<point-form />'
});

save.onclick = function validate() {
    document.getElementById("yLabel").className = "";
    document.getElementById("rLabel").className = "";
    try {
        var y = document.getElementById("yValue").value;
        y = y.replace(",", ".");
        if (y != parseFloat(y)) throw true;
        y = parseFloat(y);
        if (y > 3 || y < -5) {
            throw true;
        }
    } catch (e) {
        document.getElementById("yLabel").className = "invalid";
    }

    try {
        var r = document.getElementsByName("valueR")[8].checked ? 3 :
            document.getElementsByName("valueR")[7].checked ? 2 :
                document.getElementsByName("valueR")[6].checked ? 1 : -1;
        if (r != parseFloat(r)) throw true;
        if (r <= 0) {
            throw true;
        }
    } catch (e) {
        document.getElementById("rLabel").className = "invalid";
    }
};