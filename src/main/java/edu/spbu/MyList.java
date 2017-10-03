package edu.spbu;

import java.util.function.Function;

/**
 * Created by Миша on 24.09.2017.
 */
public class MyList<Type> {
    private Element currentEl;         //для добавления
    private Element firstEl;

    public void add(Type m) {
        if (firstEl == null)
            firstEl = new Element(m);
        else if (firstEl.pointer == null) {
            currentEl = new Element(m);
            firstEl.pointer = currentEl;
            currentEl.pointer = null;
        } else {
            Element element = new Element(m);
            currentEl.pointer = element;
            currentEl = element;
        }
    }

    public int length() {
        int i = 0;
        Element element;
        if (firstEl != null) {
            element = firstEl;
            while (element.pointer != null) {
                element = element.pointer;
                i++;
            }
            return i + 1;
        } else return 0;
    }

    public void remove(int number) throws Exception {
        if (firstEl != null) {          //если есть элементы
            if (number > 0) {           //если удаляем не нулевой
                Element before = getEl(number - 1);
                if (before != null)
                    if (before.pointer != null) {         //если удаляемый элемент существует
                        if (before.pointer.pointer != null) before.pointer = before.pointer.pointer;
                        else {
                            currentEl = before;
                            currentEl.pointer = null;
                        }
                    }
            } else {
                if (firstEl.pointer == null) firstEl = null;
                else firstEl = firstEl.pointer;
            }
        }
    }

    public Element getEl(int number) throws Exception {
        Element element;
        if (firstEl != null) {   //если есть элементы
            element = firstEl;
            for (int j = 0; j < number; j++) {
                if (element.pointer != null)
                    element = element.pointer;
                else throw new Exception();
            }
            return element;
        } else throw new Exception();
    }

    public Type get(int number) throws Exception {
        Element element;
        if (firstEl != null) {   //если есть элементы
            element = firstEl;
            for (int j = 0; j < number; j++) {
                if (element.pointer != null)
                    element = element.pointer;
                else throw new Exception();
            }
            return element.value;
        } else throw new Exception();
    }

    public void sort(MyList<Main.Matrix2x2> list) throws Exception {
        int l = list.length();
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                if (list.get(i).det() > list.get(j).det()) {
                    if (i == 0) {
                        if (l == 2) {                         //если 2 элемента
                            list.getEl(1).pointer = list.getEl(0);
                            firstEl = firstEl.pointer;
                            list.getEl(1).pointer = null;
                            currentEl = firstEl.pointer;
                        } else if (j == l - 1) {           //если менять первый с последним
                            list.getEl(l - 1).pointer = list.getEl(1);
                            Element e ;
                            e = (Element) list.getEl(j);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            e.pointer = firstEl.pointer;
                            firstEl = e;
                            list.getEl(j).pointer = null;
                            currentEl = (Element) list.getEl(j);
                        } else {                             //меняю первый с не последним
                            Element e ;
                            e = (Element) list.getEl(j);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            Element e2 ;
                            e2 = e.pointer;
                            e.pointer = firstEl.pointer;
                            ((Element) list.getEl(i)).pointer = e2;
                            firstEl = e;
                        }
                    } else if (j == l - 1) {                  //если меняю последний не с первым
                        if ((i + 1) != j) {
                            Element e ;
                            e = (Element) list.getEl(j);
                            e.pointer = (Element) list.getEl(i + 1);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            list.getEl(i).pointer = null;
                            ((Element) list.getEl(i - 1)).pointer = e;
                            currentEl = (Element) list.getEl(l - 1);
                        } else {                                //меняю последний и предпоследний
                            Element e ;
                            e = (Element) list.getEl(j - 1);
                            list.getEl(j - 2).pointer = list.getEl(j);
                            ((Element) list.getEl(j - 1)).pointer = e;
                            e.pointer = null;
                            currentEl = (Element) list.getEl(l - 1);


                        }
                    } else {           //меняю не первый и не последний
                        if ((j - i) != 1) {
                            Element e ;
                            e = (Element) list.getEl(i);
                            Element e1 ;
                            e1 = (Element) list.getEl(j - 1);
                            Element e2 ;
                            e2 = e1.pointer.pointer;
                            list.getEl(i - 1).pointer = list.getEl(j);
                            ((Element) list.getEl(i)).pointer = e.pointer;
                            e1.pointer = e;
                            e.pointer = e2;
                        } else {
                            Element e ;
                            e = (Element) list.getEl(i);
                            Element e2 ;
                            e2 = (Element) list.getEl(i+2);
                            ((Element)list.getEl(i - 1)).pointer = e.pointer;
                            ((Element)list.getEl(i)).pointer=e;
                            e.pointer=e2;
                        }
                    }
                }
            }
        }
    }

    public class Element {
        Type value;
        Element pointer;

        public Element(Type a) {
            this.pointer = null;
            this.value = a;
        }

        public Element() {
            this.pointer = null;
            this.value = null;
        }
    }

    public MyList() {

    }
}
