package org.richfaces.component;

import static org.richfaces.component.Framework.Family.cf;
import static org.richfaces.component.Framework.Family.dotNet;
import static org.richfaces.component.Framework.Family.php;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import org.richfaces.component.Framework.Family;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScope
public class DragDropBean implements Serializable {

    private static final long serialVersionUID = 1416925735640720492L;
    private static final FrameworkFamilyPredicate CF_PREDICATE = new FrameworkFamilyPredicate(cf);
    private static final FrameworkFamilyPredicate DOT_NET_PREDICATE = new FrameworkFamilyPredicate(dotNet);
    private static final FrameworkFamilyPredicate PHP_PREDICATE = new FrameworkFamilyPredicate(php);

    private static final class FrameworkFamilyPredicate implements Predicate<Framework> {

        private Framework.Family family;

        public FrameworkFamilyPredicate(Family family) {
            super();
            this.family = family;
        }

        @Override
        public boolean apply(Framework input) {
            return family.equals(input.getFamily());
        }
    }
    private List<Framework> source;
    private List<Framework> target;

    public DragDropBean() {
        initList();
    }

    public Collection<Framework> getSource() {
        return source;
    }

    public Collection<Framework> getTarget() {
        return target;
    }

    public List<Framework> getTargetPHP() {
        return Lists.newLinkedList(Collections2.filter(target, PHP_PREDICATE));
    }

    public List<Framework> getTargetDotNet() {
        return Lists.newLinkedList(Collections2.filter(target, DOT_NET_PREDICATE));
    }

    public List<Framework> getTargetCF() {
        return Lists.newLinkedList(Collections2.filter(target, CF_PREDICATE));
    }

    public void moveFramework(Framework framework) {
        source.remove(framework);
        target.add(framework);
    }

    public void reset() {
        initList();
    }

    private void initList() {
        source = Lists.newArrayList();
        target = Lists.newArrayList();

        source.add(new Framework("Flexible Ajax", php));
        source.add(new Framework("ajaxCFC", cf));
        source.add(new Framework("AJAXEngine", dotNet));
        source.add(new Framework("AjaxAC", php));
        source.add(new Framework("MonoRail", dotNet));
        source.add(new Framework("wddxAjax", cf));
        source.add(new Framework("AJAX AGENT", php));
        source.add(new Framework("FastPage", dotNet));
        source.add(new Framework("JSMX", cf));
        source.add(new Framework("PAJAJ", php));
        source.add(new Framework("Symfony", php));
        source.add(new Framework("PowerWEB", dotNet));
    }
}
