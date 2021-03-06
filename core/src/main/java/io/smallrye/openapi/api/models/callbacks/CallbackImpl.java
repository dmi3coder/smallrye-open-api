package io.smallrye.openapi.api.models.callbacks;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.microprofile.openapi.models.PathItem;
import org.eclipse.microprofile.openapi.models.callbacks.Callback;

import io.smallrye.openapi.api.constants.OpenApiConstants;
import io.smallrye.openapi.api.models.ExtensibleImpl;
import io.smallrye.openapi.api.models.ModelImpl;
import io.smallrye.openapi.runtime.util.ModelUtil;

/**
 * An implementation of the {@link Callback} OpenAPI model interface.
 */
public class CallbackImpl extends ExtensibleImpl<Callback> implements Callback, ModelImpl {

    private String ref;
    private Map<String, PathItem> pathItems;

    /**
     * @see org.eclipse.microprofile.openapi.models.Reference#getRef()
     */
    @Override
    public String getRef() {
        return ref;
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.Reference#setRef(java.lang.String)
     */
    @Override
    public void setRef(String ref) {
        if (ref != null && !ref.contains("/")) {
            ref = OpenApiConstants.REF_PREFIX_CALLBACK + ref;
        }
        this.ref = ref;
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.Reference#ref(java.lang.String)
     */
    @Override
    public Callback ref(String ref) {
        setRef(ref);
        return this;
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.callbacks.Callback#addPathItem(java.lang.String,
     *      org.eclipse.microprofile.openapi.models.PathItem)
     */
    @Override
    public Callback addPathItem(String name, PathItem item) {
        this.pathItems = ModelUtil.add(name, item, this.pathItems, LinkedHashMap<String, PathItem>::new);
        return this;
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.callbacks.Callback#removePathItem(java.lang.String)
     */
    @Override
    public void removePathItem(String name) {
        ModelUtil.remove(this.pathItems, name);
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.callbacks.Callback#getPathItems()
     */
    @Override
    public Map<String, PathItem> getPathItems() {
        return ModelUtil.unmodifiableMap(this.pathItems);
    }

    /**
     * @see org.eclipse.microprofile.openapi.models.callbacks.Callback#setPathItems(java.util.Map)
     */
    @Override
    public void setPathItems(Map<String, PathItem> items) {
        this.pathItems = ModelUtil.replace(items, LinkedHashMap<String, PathItem>::new);
    }

}