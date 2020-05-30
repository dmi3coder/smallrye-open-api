package io.smallrye.openapi.runtime.io.info;

import org.eclipse.microprofile.openapi.models.info.Info;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.openapi.api.models.info.InfoImpl;
import io.smallrye.openapi.runtime.io.IoLogging;
import io.smallrye.openapi.runtime.io.JsonUtil;
import io.smallrye.openapi.runtime.io.contact.ContactReader;
import io.smallrye.openapi.runtime.io.extension.ExtensionReader;
import io.smallrye.openapi.runtime.io.license.LicenseReader;
import io.smallrye.openapi.runtime.util.JandexUtil;

/**
 * This reads the Info from annotations or json
 * 
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md#infoObject">infoObject</a>
 * 
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 * @author Eric Wittmann (eric.wittmann@gmail.com)
 */
public class InfoReader {

    private InfoReader() {
    }

    /**
     * Annotation to Info
     * 
     * @param annotationValue the {@literal @}Info annotation
     * @return Info model
     */
    public static Info readInfo(final AnnotationValue annotationValue) {
        if (annotationValue == null) {
            return null;
        }
        IoLogging.log.annotation("@Info");
        AnnotationInstance nested = annotationValue.asNested();

        Info info = new InfoImpl();
        info.setTitle(JandexUtil.stringValue(nested, InfoConstant.PROP_TITLE));
        info.setDescription(JandexUtil.stringValue(nested, InfoConstant.PROP_DESCRIPTION));
        info.setTermsOfService(JandexUtil.stringValue(nested, InfoConstant.PROP_TERMS_OF_SERVICE));
        info.setContact(ContactReader.readContact(nested.value(InfoConstant.PROP_CONTACT)));
        info.setLicense(LicenseReader.readLicense(nested.value(InfoConstant.PROP_LICENSE)));
        info.setVersion(JandexUtil.stringValue(nested, InfoConstant.PROP_VERSION));
        return info;
    }

    /**
     * Reads an {@link Info} OpenAPI node.
     * 
     * @param node the json node
     * @return Info model
     */
    public static Info readInfo(final JsonNode node) {
        if (node == null) {
            return null;
        }
        IoLogging.log.singleJsonNode("Info");

        Info info = new InfoImpl();
        info.setTitle(JsonUtil.stringProperty(node, InfoConstant.PROP_TITLE));
        info.setDescription(JsonUtil.stringProperty(node, InfoConstant.PROP_DESCRIPTION));
        info.setTermsOfService(JsonUtil.stringProperty(node, InfoConstant.PROP_TERMS_OF_SERVICE));
        info.setContact(ContactReader.readContact(node.get(InfoConstant.PROP_CONTACT)));
        info.setLicense(LicenseReader.readLicense(node.get(InfoConstant.PROP_LICENSE)));
        info.setVersion(JsonUtil.stringProperty(node, InfoConstant.PROP_VERSION));
        ExtensionReader.readExtensions(node, info);
        return info;
    }

}
