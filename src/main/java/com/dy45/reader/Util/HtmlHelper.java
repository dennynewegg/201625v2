package com.dy45.reader.Util;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;

/**
 * Created by dy45 on 10/21/2016.
 */

public class HtmlHelper {
    public static String InnerText(String html, NodeFilter filter){
        try{
            Parser parser = new Parser();
            parser.setInputHTML(html);
            NodeList nodeList = parser.parse(filter);
            StringBuffer buffer = new StringBuffer(html.length());
            InnerText(nodeList,buffer);
            return buffer.toString();
        }
        catch (Exception ex){
            ExceptionUtil.handle(ex);
        }
        return "";
    }

//    public static String InnerXml(String html,NodeFilter filter){
//
//    }

    private static void InnerText(NodeList nodeList,StringBuffer buffer){
        if(nodeList == null) {
            return;
        }
        for (int i = 0;i<nodeList.size();i++) {
            Node node = nodeList.elementAt(i);
            if(node instanceof TextNode){
                buffer.append(node.getText());
                buffer.append("<br />");
            }
            else if(node instanceof TagNode){
                InnerText(node.getChildren(),buffer);
            }
        }
    }
}
