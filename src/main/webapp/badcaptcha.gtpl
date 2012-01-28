<html>
    <head>
        <title>Gremlin web console - Bad Captcha Result</title>

        <link rel="alternate"
                type="application/atom+xml"
                href="/atom.groovy"
                title="Gremlin Web Console snippets atom feed"
        />

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>
    </head>

    <body>
        <h1><a href="/">Gremlin web console</a></h1>

        <table cellspacing="20" style="float:right">
            <tr>
                <td>
                    <a id="atomLink" href="/atom.groovy">
                        <table>
                            <tr>
                                <td><img src="/images/atom-feed.png" alt="subscribe to the feed" align="left" border="0"></td>
                                <td><i>Subscribe<br/>to this<br/>site</i></td>
                            </tr>
                        </table>
                    </a>
                </td>
            </tr>
        </table>

        <div>
            <h2>You entered a wrong captcha answer, please try again.</h2>

            <div id="actionsBreadcrumb">
                <span class="actionsBreadcrumbHead">Actions &nbsp;&#x27A4;</span>
                <span class="actionsBreadcrumbChild"><a href="/">Back to console</a></span>
                <span class="actionsBreadcrumbLastChild"><a href="/scripts">View all recent scripts</a></span>
            </div>
        </div>

        <% include '/WEB-INF/includes/about.gtpl' %>
    </body>
</html>