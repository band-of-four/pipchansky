<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>IAD lab1</title>
    <style>
        html {
            font-family: monospace;
            font-size: 14px;
        }

        html, body {
            margin: 0;
            padding: 0;
        }

        #header {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            font-size: 16px;
            color: black;
            margin: 0 0 20px;
        }

        body {
            display: flex;
        }

        .field {
            margin: 10px;
            padding: 1.5em;
            background: #aadcff;

            -webkit-box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.7);
            -moz-box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.7);
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.7);
        }

        .field > * {
            margin-left: auto;
            margin-right: auto;
        }

        .field.grow {
            flex-shrink: 0;
            flex-grow: 1;
        }

        .container {
            margin: 0 30px;
            flex-grow: 1;
            display: flex;
        }

        .container.horizontal {
            margin: 0;
            flex-direction: row;
        }

        .container.vertical {
            margin: 0;
            flex-direction: column;
        }

        .field > img {
            border-radius: 7px;
            margin: 5px;
            display: block;
        }

        button, input[type = "submit"] {
            background: #e6f1ff;
            border: 0;
            width: 40px;
            height: 40px;
            font-weight: bold;
            cursor: pointer;

            -webkit-box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.7);
            -moz-box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.7);
            box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.7);
        }

        input[type = "submit"] {
            width: calc(100% - 10px);
        }

        input[type = "text"] {
            padding: 3px;
        }

        td > * {
            margin: 5px;
        }

        #results td, #results th {
            text-align: center;
            padding: 0.5em;
            border: 1px solid black;
        }

        #results {
            border: 2px solid black;
            border-collapse: collapse;
        }

        button:hover {
            background: #d6e0ee;
        }

        button:active, button.active {
            background: #b9c3d1;
        }

        .hidden {
            display: none;
        }

        #inputs > * {
            margin-top: 10px;
            margin-bottom: 10px;
            padding-right: 3em;
        }

        .invalid {
            box-shadow: 0 0 7px 0 red;
            background: rgba(255, 0, 0, 0.38);
            border-color: red;
        }

        #task {
            align-self: flex-start;
        }

        #results_field {
            max-height: calc((20px + 1em) * 10 + 20px);
            overflow-y: auto;
        }

        form {
            width: max-content;
        }

        input::placeholder {
            color: green;
        }

        @media screen and (max-width: 1280px) {
            .container.horizontal {
                flex-direction: column-reverse;
            }

            #task {
                align-self: unset;
            }

            #task img {
                display: inline-block;
            }

            #task_img {
                display: block;
                max-width: 100%;
                height: auto;
            }

            .field, .container {
                max-width: 100%;
            }

            #results td, #results th {
                font-size: 11px;
            }

            #header {
                font-size: 12px;
            }

            .res_elem {
            }
        }


    </style>
	<script>
	
	var points = [];
	
	
	function redraw (elem){
		elem.value = elem.value.replace(/[^\d,.-]/g, '');
		var r = parseFloat(elem.value.replace(",", "."));
		if (!isNaN(r) && r >= 2 && r <= 5){
			$(".point").remove();
			$("#r").removeClass("invalid");
			document.getElementById('graph_x').innerHTML = r;
			document.getElementById('graph_y').innerHTML = r;
			
			for (let i=0; i<points.length; i++) {
				
				//точка
				var c = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
				if (points[i][0] < 10) {
					var cx = (points[i][0] * 150) / r + 170; 
					var x = points[i][0];
				} else {
					var cx = points[i][0];
					var x = (points[i][0]-170)*r/150;
				}
				c.setAttribute('cx',cx);
				c.setAttribute('class','point');
				if (points[i][0] < 10) {
					var cy = 170 - points[i][1] * 150 / r;
					var y = points[i][1];
				} else {
					var cy = points[i][1];
					var y = -(points[i][1]-170)*r/150;
				}
				c.setAttribute('cy',cy);
				c.setAttribute('r','3');
				c.setAttribute('stroke', 'black');
				console.log(x, y);
			
				if ( (x >= 0.0 && x <= r && y >= -r && y <= 0.0 && Math.sqrt(x * x + y * y) <= r)|| 
					 (x >= 0 && x <= r/2 && y >= 0.0 && y <= r) ||
					 (x >= -r && x <= 0.0 && y >= -r / 2 && y <= 0.0 && (-x - 2*y) <= r)) {
						c.style.fill='green';
					} else {
						c.style.fill='red';
					}
				
				document.getElementById('graph').appendChild(c);
			}
		} else {
			$("#r").addClass("invalid");
		}
	}
	
	function validate_y (elem){
		elem.value = elem.value.replace(/[^\d,.-]/g, '');
	}
	</script>
</head>
<body>

<div class="container vertical">
    <div class="field" id="header">
        <div class="left">Группа: P3202</div>
        <div class="middle">Каюков Иван, Калугина Марина</div>
        <div class="right">Вариант: 78638</div>
    </div>
    <div class="container horizontal">
        <div class="container vertical">
            <div id="inputs" class="field grow">
                <form id="form">
                    <input type="hidden" id="x" name="x">
                    <table>
                        <tr>
                            <td rowspan="3"><label for="x">X =</label></td>
                            <td>
                                <button>-5</button>
                            </td>
                            <td>
                                <button>-4</button>
                            </td>
                            <td>
                                <button>-3</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button>-2</button>
                            </td>
                            <td>
                                <button>-1</button>
                            </td>
                            <td>
                                <button>0</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button>1</button>
                            </td>
                            <td>
                                <button>2</button>
                            </td>
                            <td>
                                <button>3</button>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="y">Y =</label></td>
                            <td colspan="3"><input required id="y" name="y" type="text" placeholder="(-3 ... 3)"
                                                   oninput="validate_y(this)"></td>
                        </tr>
                        <tr>
                            <td><label for="r">R =</label></td>
                            <td colspan="3"><input required id="r" name="r" type="text" placeholder="(2 ... 5)"
                                                   oninput="redraw(this)"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="3"><input type="submit" value="Отправить"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="results_field" class="field grow hidden">
                <table id="results">
                    <tr>
                        <th>N</th>
                        <th>Y</th>
                        <th>X</th>
                        <th>R</th>
                        <th>&isin;</th>
                        <th>Current time</th>
                        <th>Execution time</th>
                    </tr>
                </table>
            </div>
        </div>
        <div id="task" class="field">
            <svg id="graph" width="340" height="340">
				<rect x="50%" y="20" width="75" height="150" fill="blue" />
				<path d="M 170 310 A 200 200 0 0 0 310 170 L 170 170 Z" fill="blue" stroke="none" fill-rule="evenodd"/>
				<polygon points="170,170 20,170 170,245" fill="blue"/>
				<text id="graph_x" x="305" y="184" font-family="monospace" font-size="20"
					  fill="white" stroke="black">
						R
				</text>
				<text id="graph_y" y="25" x="174" font-family="monospace" font-size="20"
					fill="white" stroke="black">
						R
				</text>

				<line x1="50%" y1="0%" x2="50%" y2="100%" style="stroke:rgb(0,0,0);stroke-width:2" />
				<line x1="0%" y1="50%" x2="100%" y2="50%" style="stroke:rgb(0,0,0);stroke-width:2" />
			</svg>
        </div>
    </div>
</div>

<script src="static/js/jQuery.min.js"></script>
<script>

    function sendRequestWithCoordinates(x, y, radius) {
        fetch('AreaCheckServlet/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: "x=" + x.toString() + "&y=" + y.toString() + "&r=" + radius.toString()
        }).then(response => response.json())
            .then(data => {

                var jsonArrStr = '[' + data.toString() + ']';
                console.log(jsonArrStr);

                var arr = JSON.parse(jsonArrStr);

                document.getElementById("results").innerHTML = "<tr>" +
                    "<th>N</th>" +
                    "<th>Y</th>" +
                    "<th>X</th>" +
                    "<th>R</th>" +
                    "<th>&isin;</th></tr>";

                var counter = 1;
                arr.forEach(function (elem) {
                    console.log(elem);

                    points.push([elem["x"], elem["y"]]);

                    document.getElementById("results").innerHTML +=
                        "<tr class='res_elem'><td>" + counter +
                        "</td><td>" + elem["x"] +
                        "</td><td>" + elem["y"] +
                        "</td><td>" + elem["radius"] +
                        "</td><td>" + elem["isIn"] +
                        "</td></tr>";
                    counter++;
                });

                $("#results_field").removeClass("hidden");
                redraw(document.getElementById('r'));
            }).catch(err => {
            console.log(err)
        });
    }

	var pt = document.getElementById('graph').createSVGPoint();
	
	graph.onclick = function(event) {
		pt.x = event.clientX;
		pt.y = event.clientY;
		var cursorpt =  pt.matrixTransform(document.getElementById('graph').getScreenCTM().inverse());
		console.log("(" + cursorpt.x + ", " + cursorpt.y + ")");
		if ((document.getElementById('r').value)>=2) {
			points.push([cursorpt.x, cursorpt.y]);
		}
		redraw(document.getElementById('r'));
	};


    var x = null;
    $(".container .container table button").on("click", function (e) {
        e.preventDefault();
        if (x === parseInt(e.currentTarget.innerText)) {
            x = null;
            document.querySelector("#x").value = x;
            $("table button").removeClass("active");
            return;
        }
        x = parseInt(e.currentTarget.innerText);
        document.querySelector("#x").value = x;
        $("table button").removeClass("active");
        $(e.currentTarget).addClass("active");
    });

    $("form#form").on("submit", function (e) {
        e.preventDefault();
        var fd = new FormData(e.currentTarget);
        var x = parseInt(fd.get("x"));
        var y = fd.get("y");
        y = y.replace(",", ".");
        var r = fd.get("r");
        r = r.replace(",", ".");
        var endOfLight = false;
        $("label").removeClass("invalid");
        if (!(x <= 3 && x >= -5)) {
            $("#inputs table button").addClass("invalid");
            endOfLight = true;
        }

        try {
            if (y != parseFloat(y)) throw true;
            y = parseFloat(y);
            if (y > 3 || y < -3) {
                throw true;
            }
        } catch (e) {
            $("#y").addClass("invalid");
            endOfLight = true;
        }

        try {
            // using != instead of !== cause they have different types
            if (r != parseFloat(r)) throw true;
            r = parseFloat(r);
            if (r > 5 || r < 2) {
                throw true;
            }
        } catch (e) {
            $("#r").addClass("invalid");
            endOfLight = true;
        }

        if (endOfLight) return;

        const params = new URLSearchParams();
        for (const pair of fd.entries()) {
            params.append(pair[0], pair[1]);
        }

        sendRequestWithCoordinates(params.get("x"), params.get("y"), params.get("r"));
    });

    $("input[type = text]").on("click", function (event) {
        $(event.currentTarget).removeClass("invalid");
    });

    $("#inputs table button").on("click", function (event) {
        $("#inputs table button").removeClass("invalid");
    });
</script>

</body>
</html>
