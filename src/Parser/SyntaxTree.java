package Parser;

public interface SyntaxTree {




     public abstract SyntaxTreeType getType();

    /**
     *
     * Returns the children that coincides with that type
     * incase there are multiple it will return the next child of the same type
     * When all the children of the same have been given, it will return null
     * @param type
     * @return
     */
    public abstract SyntaxTree getChildren(SyntaxTreeType type);

}

