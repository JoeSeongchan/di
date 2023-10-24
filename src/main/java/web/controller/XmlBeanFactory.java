package web.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Constructor;
import java.util.*;

public class XmlBeanFactory {

    // (ID, 객체)
    // 예시: ("member-service", MemberService 객체)
    Map<String, Object> objectMap;


    public XmlBeanFactory(String path) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        objectMap = new HashMap<>();
        parser.parse(path, new MyDefaultHandler());
    }

    static class Bean {


        String id, className;
        List<Bean> parents;
        List<Bean> childs;

        public Bean(String id, String className) {
            this.id = id;
            this.className = className;
            parents = new ArrayList<>();
            childs = new ArrayList<>();
        }


        public void addParent(Bean parent) {
            if (parents.contains(parent)) {
                return;
            }
            parents.add(parent);
        }

        public void addChild(Bean child) {
            if (childs.contains(child)) {
                return;
            }
            childs.add(child);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Bean bean = (Bean) o;
            return Objects.equals(id, bean.id) && Objects.equals(className,
                    bean.className);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, className);
        }
    }

    class MyDefaultHandler extends DefaultHandler {

        ArrayDeque<Bean> beanDeque = new ArrayDeque<>();
        // (id, Bean객체)
        // (member-service, MemberService 객체)
        Map<String, Bean> beanMap = new HashMap<>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            if ("beans".equals(qName)) {
                return;
            }
            String id = attributes.getValue("id");
            if (attributes.getValue("class") != null) {
                String className = attributes.getValue("class");
                Bean cur = new Bean(id, className);
                beanMap.put(id, cur);
                return;
            }
            Bean cur = beanMap.get(id);
            if (beanDeque.isEmpty()) {
                beanDeque.offerLast(cur);
                return;
            }
            cur.addParent(beanDeque.peekLast());
            beanDeque.peekLast().addChild(cur);
            beanDeque.offerLast(cur);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            beanDeque.pollLast();
        }

        @Override
        public void endDocument() throws SAXException {
            // 그래프가 완성되었으므로 위상정렬 알고리즘 적용한다.
            Queue<Bean> queue = new ArrayDeque<>();
            // 진입 차수 기록
            Map<String, Integer> inEdgeNs = new HashMap<>();
            for (Bean bean : beanMap.values()) {
                inEdgeNs.put(bean.id, bean.childs.size());
                if (bean.childs.isEmpty()) {
                    queue.offer(bean);
                }
            }

            while (!queue.isEmpty()) {
                Bean bean = queue.poll();
                Object[] parameters = new Object[bean.childs.size()];
                // 파라미터로 들어갈 객체 모음
                int i = 0;
                for (Bean child : bean.childs) {
                    parameters[i++] = objectMap.get(child.id);
                }
                try {
                    Constructor<?>[] ctors = Class.forName(bean.className)
                            .getConstructors();
                    Object beanObj = null;
                    for (Constructor<?> ctor : ctors) {
                        if (ctor.getParameterCount() == 0 && parameters.length != 0) {
                            continue;
                        }
                        beanObj = ctor.newInstance(
                                parameters);
                        break;
                    }

                    objectMap.put(bean.id, beanObj);
                    for (Bean parent : bean.parents) {
                        inEdgeNs.put(parent.id, inEdgeNs.get(parent.id) - 1);
                        if (inEdgeNs.get(parent.id) == 0) {
                            queue.offer(parent);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
