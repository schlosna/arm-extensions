package com.google.code.arm.lang;

/**
 * This immutable class provides the ability to wrap an instance of {@code T} for delegation. This class only delegates
 * {@link #toString()}, subclasses may override {@link #equals(Object)} and {@link #hashCode()} to conform to their
 * respective contracts.
 * 
 * @param <T>
 *            The type of delegate {@link Object}
 */
public abstract class DelegatingObject<T> {

    private final T delegate;

    public DelegatingObject(T delegate) {
        if (delegate == null) {
            throw new NullPointerException("Delgate cannot be null");
        }
        this.delegate = delegate;
    }

    /**
     * Returns a string representation of the delegate object from it's {@link #toString()} method.
     */
    @Override
    public String toString() {
        return delegate().toString();
    }

    /**
     * Returns the delegate instance
     * 
     * @return the delegate
     */
    public T delegate() {
        return this.delegate;
    }
}
