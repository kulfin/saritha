package fi.hut.soberit.agilefant.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import fi.hut.soberit.agilefant.util.AgilefantBuild;

public class CssTag extends StaticResourceTag {

    @Override
    protected void process(JspWriter out, String path) throws IOException {
        out.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        out.append("static/css/");
        out.append(path);
        out.append(".css?");
        out.append(Long.toString(AgilefantBuild.getBuild().buildTimestamp.getMillis()));
        out.append("\" />");
    }

}
