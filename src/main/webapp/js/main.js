$(document).ready(function () {
    $(function () {
        $("#tabs").tabs();
        $("#textarea-container").resizable({ handles:'s', alsoResize:'iframe' });

        $("#dialog").dialog({
            bgiframe:true,
            autoOpen:false,
            height:400,
            width:330,
            modal:true,
            buttons:{
                Submit:function () {
                    $("#captchaQuestion").val($("#dialogCaptchaQuestion").val());
                    $("#captchaAnswer").val($("#dialogCaptchaAnswer").val());
                    $("#title").val($("#dialogTitle").val());
                    $("#title").val($("#dialogTitle").val());
                    $("#author").val($("#dialogAuthor").val());
                    $("#tags").val($("#dialogTags").val());
                    $("#script").val(editor.getCode());
                    $("#publishform").submit();
                }
            }
        });


        $("#tinkerGraphInfoDialog").dialog({
            bgiframe:true,
            autoOpen:false,
            height:610,
            position:['right', 20],
            width:590,
            modal:false


        });

        $("#moviesGraphInfoDialog").dialog({
            bgiframe:true,
            autoOpen:false,
            position:['right', 20],
            height:500,
            width:430,
            modal:true


        });
    });

    $("#captchaOperation").load("/captchaquestion.groovy", function (responseText) {
        $("#dialogCaptchaQuestion").val(responseText);
    });

    $("#publishButton").click(function (event) {
        var code = editor.getCode();
        // better trim() function than JQuery's
        if (code.replace(/^\s+|\s+$/g, '').length > 0) {
            $('#dialog').dialog('open');
            event.preventDefault();
        } else {
            alert("Please enter a script before publishing.");
            event.preventDefault();
        }
    });

    $("#graphInfoButton").click(function (event) {
        var graphOption = $('#graphOption').val()
        if (graphOption.indexOf("TinkerGraph") != -1) {
            $('#tinkerGraphInfoDialog').dialog('open');
            event.preventDefault();
        }

        if (graphOption.indexOf("createMovieGraph") != -1) {
            $('#moviesGraphInfoDialog').dialog('open');
            event.preventDefault();
        }

    });

    $("#executeButton").click(function (event) {
        $.ajax({
            type:"POST",
            url:"/executor.groovy",
            data:{ script:editor.getCode() },
            dataType:"json",
            complete:function (xhr, status) {
                if (status === 'error' || !xhr.responseText) {
                    alert("Error interacting with the Groovy web console server: " + status);
                } else {
                    var data;
                    try {
                        data = $.parseJSON(xhr.responseText);
                    } catch (e) {
                        alert("Impossible to parse JSON response: " + e);
                        data = {executionResult:"", outputText:"", stacktraceText:""};
                    }

                    $('#output').text(data.outputText);
                    $('#result').text(data.executionResult);
                    $('#stacktrace').text(data.stacktraceText);

                    if (data.outputText.length > 0) {
                        $("#tabs").tabs('select', 1);
                        $('#output').text(data.outputText).fadeIn();
                    } else {
                        $('#output').fadeOut();
                    }

                    if (data.executionResult.length > 0) {
                        if (data.executionResult != "null") {
                            $("#tabs").tabs('select', 0);
                        }
                        $('#result').text(data.executionResult).fadeIn();
                    } else {
                        $('#result').fadeOut();
                    }

                    if (data.stacktraceText.length > 0) {
                        $("#tabs").tabs('select', 2);
                        $('#stacktrace').text(data.stacktraceText).fadeIn();
                    } else {
                        $('#stacktrace').fadeOut();
                    }
                }
            }
        });
    });

    $('#loadingDiv')
        .hide()
        .ajaxStart(function () {
            $(this).show();
        })
        .ajaxStop(function () {
            $(this).hide();
        });


    $('#loadGraphButton').click(function () {
        editor.setCode($('#graphOption').val());
        var graphOption = $('#graphOption').val()
        if (graphOption.indexOf("MovieGraphFactory") != -1) {
            $.ajax({
                type:"POST",
                url:"/loadMovieGraph.groovy",
                data:{},
                dataType:"json"
            });

        }

    });


});


