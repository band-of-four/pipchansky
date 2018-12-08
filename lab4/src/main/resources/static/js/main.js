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
        '<label>Y</label>'+
        '<input type="text" placeholder="Write y" v-model="yValue" />' +
        '<br/>'+
        '<label>R</label>'+
        '<input class="w3-radio" type="radio" id="Yminus5" name="valueY" value="-5" v-model="rValue" />'+
        '<label for="Xminus5">-5</label>'+
        '<input class="w3-radio" type="radio" id="Yminus4" name="valueY" value="-4" v-model="rValue" />'+
        '<label for="Xminus4">-4</label>'+
        '<input class="w3-radio" type="radio" id="Yminus3" name="valueY" value="-3" v-model="rValue" />'+
        '<label for="Xminus3">-3</label>'+
        '<input class="w3-radio" type="radio" id="Yminus2" name="valueY" value="-2" v-model="rValue" />'+
        '<label for="Xminus2">-2</label>'+
        '<input class="w3-radio" type="radio" id="Yminus1" name="valueY" value="-1" v-model="rValue" />'+
        '<label for="Xminus1">-1</label>'+
        '<input class="w3-radio" type="radio" id="Yplus0" name="valueY" value="0" v-model="rValue" />'+
        '<label for="Xplus0">0</label>'+
        '<input class="w3-radio" type="radio" id="Yplus1" name="valueY" value="1" v-model="rValue" />'+
        '<label for="Xplus1">1</label>'+
        '<input class="w3-radio" type="radio" id="Yplus2" name="valueY" value="2" v-model="rValue" />'+
        '<label for="Xplus2">2</label>'+
        '<input class="w3-radio" type="radio" id="Yplus3" name="valueY" value="3" v-model="rValue" />'+
        '<label for="Xplus3">3</label>'+
        '<br/>'+
        '<input type="button" value="Save" @click="save" />' +
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