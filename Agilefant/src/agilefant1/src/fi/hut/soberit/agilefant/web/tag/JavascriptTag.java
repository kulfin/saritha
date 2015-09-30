package fi.hut.soberit.agilefant.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import fi.hut.soberit.agilefant.util.AgilefantBuild;

public class JavascriptTag extends StaticResourceTag {

    @Override
    protected void process(JspWriter out, String path) throws IOException {
        out.append("<script type=\"text/javascript\" src=\"");
        out.append("static/js/");
        out.append(path);
        out.append(".js?");
        out.append(Long.toString(AgilefantBuild.getBuild().buildTimestamp.getMillis()));
        out.append("\"></script>");
    }

}
