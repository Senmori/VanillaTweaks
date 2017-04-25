package net.senmori.vanillatweaks.util;

import java.util.Locale;
import org.apache.commons.lang3.Validate;

public class ResourceIdentifier {
    private static final String DEFAULT_DOMAIN = "minecraft";
    protected final String resourceDomain;
    protected final String resourcePath;

    protected ResourceIdentifier(String... resourceName) {
        this.resourceDomain = org.apache.commons.lang3.StringUtils.isEmpty(resourceName[0]) ? DEFAULT_DOMAIN : resourceName[0].toLowerCase(Locale.ROOT);
        this.resourcePath = resourceName[1].toLowerCase(Locale.ROOT);
        Validate.notNull(this.resourcePath);
    }

    public ResourceIdentifier(String resourceName) {
        this(splitObjectName(resourceName));
    }

    public ResourceIdentifier(String resourceDomainIn, String resourcePathIn) {
        this(new String[]{resourceDomainIn, resourcePathIn});
    }

    /**
     * Splits an object name (such as minecraft:apple) into the domain and path parts and returns these as an array of
     * length 2. If no colon is present in the passed value the returned array will contain {null, toSplit}.
     */
    protected static String[] splitObjectName(String toSplit) {
        String[] astring = new String[] {DEFAULT_DOMAIN, toSplit};
        int i = toSplit.indexOf(58);

        if(i >= 0) {
            astring[1] = toSplit.substring(i + 1, toSplit.length());

            if(i > 1) {
                astring[0] = toSplit.substring(0, i);
            }
        }

        return astring;
    }

    public String getResourceIdentifier() {
        return this.resourcePath;
    }

    public String getResourceDomain() {
        return this.resourceDomain;
    }

    public String toString() {
        return this.resourceDomain + ':' + this.resourcePath;
    }

    public boolean equals(Object other) {
        if(this == other) {
            return true;
        } else
            if(! ( other instanceof ResourceIdentifier )) {
                return false;
            } else {
                ResourceIdentifier resourcelocation = (ResourceIdentifier) other;
                return this.resourceDomain.equals(resourcelocation.resourceDomain) && this.resourcePath.equals(resourcelocation.resourcePath);
            }
    }

    public int hashCode() {
        return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
    }
}