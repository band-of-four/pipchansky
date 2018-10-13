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
</head>
<body>

<div class="container vertical">
    <div class="field" id="header">
        <div class="left">Группа: P3102</div>
        <div class="middle">Каюков Иван</div>
        <div class="right">Вариант: 18205</div>
    </div>
    <div class="container horizontal">
        <div class="container vertical">
            <div id="inputs" class="field grow">
                <form id="form">
                    <input type="hidden" id="y" name="y">
                    <table>
                        <tr>
                            <td rowspan="3"><label for="y">Y =</label></td>
                            <td>
                                <button>-3</button>
                            </td>
                            <td>
                                <button>-2</button>
                            </td>
                            <td>
                                <button>-1</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button>0</button>
                            </td>
                            <td>
                                <button>1</button>
                            </td>
                            <td>
                                <button>2</button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button>3</button>
                            </td>
                            <td>
                                <button>4</button>
                            </td>
                            <td>
                                <button>5</button>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="x">X =</label></td>
                            <td colspan="3"><input required id="x" name="x" type="text" placeholder="(-3 ... 3)"></td>
                        </tr>
                        <tr>
                            <td><label for="r">R =</label></td>
                            <td colspan="3"><input required id="r" name="r" type="text" placeholder="(2 ... 5)"></td>
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
            <img src="static/images/graph.jpg" alt="This is a graph">
            <img id="task_img" src="static/images/task.png" alt="This is a task">
        </div>
    </div>
</div>

<script src="static/js/jQuery.min.js"></script>
<script>
    var counter = 1;
    var y = null;
    $(".container .container table button").on("click", function (e) {
        e.preventDefault();
        if (y === parseInt(e.currentTarget.innerText)) {
            y = null;
            document.querySelector("#y").value = y;
            $("table button").removeClass("active");
            return;
        }
        y = parseInt(e.currentTarget.innerText);
        document.querySelector("#y").value = y;
        $("table button").removeClass("active");
        $(e.currentTarget).addClass("active");
    });

    $("form#form").on("submit", function (e) {
        e.preventDefault();
        var fd = new FormData(e.currentTarget);
        var y = parseInt(fd.get("y"));
        var x = fd.get("x");
        x = x.replace(",", ".");
        var r = fd.get("r");
        r = r.replace(",", ".");
        var endOfLight = false;
        $("label").removeClass("invalid");
        if (!(y <= 5 && y >= -3)) {
            $("#inputs table button").addClass("invalid");
            endOfLight = true;
        }

        try {
            if (x != parseFloat(x)) throw true;
            x = parseFloat(x);
            if (x >= 3 || x <= -3) {
                throw true;
            }
        } catch (e) {
            $("#x").addClass("invalid");
            endOfLight = true;
        }

        try {
            // using != instead of !== cause they have different types
            if (r != parseFloat(r)) throw true;
            r = parseFloat(r);
            if (r >= 5 || r <= 2) {
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

        fetch('AreaCheckServlet/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            },
            body: params.toString()
        }).then(response => {
            return response.json();
        }).then(data => {

            $(".res_elem").remove();
            counter = 1;
            const add_html = "<tr class='res_elem'><td>" + counter +
                "</td><td>" + data["y"] +
                "</td><td>" + data["x"] +
                "</td><td>" + data["radius"] +
                "</td><td>" + data["isIn"] +
                "</td><td>" + 0.000 +
                "</td><td>" + 0.000 +
                "</td></tr>";
            document.getElementById("results").innerHTML += add_html;
            $("#results_field").removeClass("hidden");
            counter++;

            console.log(data); // FIXME debug
        }).catch(err => {
            console.log(err)
        });
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
