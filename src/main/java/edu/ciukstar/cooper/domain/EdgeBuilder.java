package edu.ciukstar.cooper.domain;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class EdgeBuilder {

    public Edge emptyVertexForGraph(Graph graph) {
        return new Edge(graph);
    }

    public static TargetStep source(Status s) {
        return new Steps(s);
    }

    public interface SourceStep {

        TargetStep source(Status source);
    }

    public interface TargetStep {

        GraphStep target(Status target);
    }

    public interface GraphStep {

        LabelStep graph(Graph graph);
        LabelStep noGraph();
        Edge get();
    }

    public interface LabelStep {

        TransitionNameStep label(String label);

        Edge get();
    }

    public interface TransitionNameStep {

        IconStep transitionName(String name);

        Edge get();
    }

    public interface IconStep {

        DescriptionStep icon(String waesome);

        DescriptionStep noIcon();

        Edge get();
    }

    public interface DescriptionStep {

        BuildStep description(String desc);

        BuildStep noDescription();
    }

    public interface BuildStep {

        Edge get();
    }

    private static class Steps implements SourceStep, TargetStep, LabelStep,
            TransitionNameStep, IconStep, DescriptionStep, GraphStep, BuildStep {

        private Status source;
        private Status target;
        private String label;
        private String transitionName;
        private String icon;
        private String description;
        private Graph graph;

        Steps(Status source) {
            this.source = source;
        }

        @Override
        public TargetStep source(Status source) {
            this.source = source;
            return this;
        }

        @Override
        public GraphStep target(Status target) {
            this.target = target;
            return this;
        }

        @Override
        public LabelStep graph(Graph graph) {
            this.graph = graph;
            return this;
        }

        @Override
        public LabelStep noGraph() {
            this.graph = null;
            return this;
        }

        @Override
        public TransitionNameStep label(String label) {
            this.label = label;
            return this;
        }

        @Override
        public IconStep transitionName(String name) {
            this.transitionName = name;
            return this;
        }

        @Override
        public DescriptionStep icon(String waesome) {
            this.icon = waesome;
            return this;
        }

        @Override
        public DescriptionStep noIcon() {
            this.icon = null;
            return this;
        }

        @Override
        public BuildStep description(String desc) {
            this.description = desc;
            return this;
        }

        @Override
        public BuildStep noDescription() {
            this.description = null;
            return this;
        }

        @Override
        public Edge get() {
            Edge edge = new Edge();
            edge.setSource(source);
            edge.setTarget(target);
            edge.setLabel(label);
            edge.setTransitionName(transitionName);
            edge.setIcon(icon);
            edge.setDescription(description);
            edge.setGraph(graph);
            return edge;
        }
    }
}
