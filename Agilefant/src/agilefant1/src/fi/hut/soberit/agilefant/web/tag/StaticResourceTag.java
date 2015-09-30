package fi.hut.soberit.agilefant.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import fi.hut.soberit.agilefant.util.AgilefantBuild;

public abstract class StaticResourceTag extends SimpleTagSupport {

    private static final String MINIFY_ALWAYS = "always";

    private String minify;

    private String path;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        boolean minifyNow = MINIFY_ALWAYS.equals(minify) || (AgilefantBuild.getBuild().releaseMode && Boolean.valueOf(minify));
        String finalPath = minifyNow ? path + ".min" : path;

        process(out, finalPath);
    }

    protected abstract void process(JspWriter out, String path) throws IOException;

    public void setPath(String path) {
        this.path = path;
    }

    public void setMinify(String minify) {
        this.minify = minify;
    }

}
