package edu.ciukstar.cooper.domain;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class GraphBuilder {

    public Graph emptyGraph() {
        return new Graph();
    }
    
    public static CodeStep instance() {
        return new Steps();
    }
    
    public static NameStep code(String code) {
        return new Steps(code);
    }
    
    public interface CodeStep {
        NameStep code(String code);
    }
    public interface NameStep {
        DescriptionStep name(String name);
        DescriptionStep nameAsCode();
    }
    public interface DescriptionStep {
        BuildStep description(String desctiption);
        BuildStep noDescription();
        
    }
    public interface BuildStep {
        Graph get();
    }
    
    private static class Steps implements CodeStep, NameStep, DescriptionStep, BuildStep {

        private String code;
        private String name;
        private String description;

        public Steps(String code) {
            this.code = code;
        }

        public Steps() {
            this(null);
        }

        @Override
        public NameStep code(String code) {
            this.code = code;
            return this;
        }

        @Override
        public DescriptionStep name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public DescriptionStep nameAsCode() {
            this.name = code;
            return this;
        }

        @Override
        public BuildStep description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public BuildStep noDescription() {
            this.description = null;
            return this;
        }

        @Override
        public Graph get() {
            Graph graph = new Graph();
            graph.setCode(code);
            graph.setName(name);
            graph.setDescription(description);
            return graph;
        }
        
    }
}
