package com.example.model;

/**
 * Language and Locale enumeration supporting countries worldwide
 * Format: language-COUNTRY (ISO 639-1 and ISO 3166-1)
 */
public enum LanguageLocale {
    // English variants
    EN_US("en-US", "English - United States"),
    EN_GB("en-GB", "English - United Kingdom"),
    EN_CA("en-CA", "English - Canada"),
    EN_AU("en-AU", "English - Australia"),
    EN_NZ("en-NZ", "English - New Zealand"),
    EN_IE("en-IE", "English - Ireland"),
    EN_ZA("en-ZA", "English - South Africa"),
    EN_IN("en-IN", "English - India"),
    EN_SG("en-SG", "English - Singapore"),
    EN_PH("en-PH", "English - Philippines"),
    EN("en", "English - Generic"),

    // Swedish variants
    SV_SE("sv-SE", "Swedish - Sweden"),
    SV_FI("sv-FI", "Swedish - Finland"),
    SV("sv", "Swedish - Generic"),
    SE("se", "Swedish - Alternative Code"),

    // Norwegian
    NB_NO("nb-NO", "Norwegian Bokmål - Norway"),
    NN_NO("nn-NO", "Norwegian Nynorsk - Norway"),

    // Danish
    DA_DK("da-DK", "Danish - Denmark"),

    // Finnish
    FI_FI("fi-FI", "Finnish - Finland"),

    // German variants
    DE_DE("de-DE", "German - Germany"),
    DE_AT("de-AT", "German - Austria"),
    DE_CH("de-CH", "German - Switzerland"),
    DE_LI("de-LI", "German - Liechtenstein"),

    // French variants
    FR_FR("fr-FR", "French - France"),
    FR_CA("fr-CA", "French - Canada"),
    FR_BE("fr-BE", "French - Belgium"),
    FR_CH("fr-CH", "French - Switzerland"),
    FR_LU("fr-LU", "French - Luxembourg"),
    FR_MA("fr-MA", "French - Morocco"),
    FR_SN("fr-SN", "French - Senegal"),
    FR_CI("fr-CI", "French - Côte d'Ivoire"),
    FR_CM("fr-CM", "French - Cameroon"),

    // Italian
    IT_IT("it-IT", "Italian - Italy"),
    IT_CH("it-CH", "Italian - Switzerland"),

    // Spanish variants
    ES_ES("es-ES", "Spanish - Spain"),
    ES_MX("es-MX", "Spanish - Mexico"),
    ES_AR("es-AR", "Spanish - Argentina"),
    ES_CO("es-CO", "Spanish - Colombia"),
    ES_PE("es-PE", "Spanish - Peru"),
    ES_VE("es-VE", "Spanish - Venezuela"),
    ES_CL("es-CL", "Spanish - Chile"),
    ES_EC("es-EC", "Spanish - Ecuador"),
    ES_BO("es-BO", "Spanish - Bolivia"),
    ES_PY("es-PY", "Spanish - Paraguay"),
    ES_UY("es-UY", "Spanish - Uruguay"),
    ES_SV("es-SV", "Spanish - El Salvador"),
    ES_GT("es-GT", "Spanish - Guatemala"),
    ES_HN("es-HN", "Spanish - Honduras"),
    ES_NI("es-NI", "Spanish - Nicaragua"),
    ES_PA("es-PA", "Spanish - Panama"),
    ES_DO("es-DO", "Spanish - Dominican Republic"),
    ES_CU("es-CU", "Spanish - Cuba"),
    ES_PR("es-PR", "Spanish - Puerto Rico"),

    // Portuguese variants
    PT_PT("pt-PT", "Portuguese - Portugal"),
    PT_BR("pt-BR", "Portuguese - Brazil"),
    PT_AO("pt-AO", "Portuguese - Angola"),
    PT_MZ("pt-MZ", "Portuguese - Mozambique"),
    PT_TL("pt-TL", "Portuguese - East Timor"),
    PT_GW("pt-GW", "Portuguese - Guinea-Bissau"),

    // Dutch variants
    NL_NL("nl-NL", "Dutch - Netherlands"),
    NL_BE("nl-BE", "Dutch - Belgium"),
    NL_SR("nl-SR", "Dutch - Suriname"),
    NL_AW("nl-AW", "Dutch - Aruba"),

    // Polish
    PL_PL("pl-PL", "Polish - Poland"),

    // Czech
    CS_CZ("cs-CZ", "Czech - Czech Republic"),

    // Slovak
    SK_SK("sk-SK", "Slovak - Slovakia"),

    // Hungarian
    HU_HU("hu-HU", "Hungarian - Hungary"),

    // Romanian variants
    RO_RO("ro-RO", "Romanian - Romania"),
    RO_MD("ro-MD", "Romanian - Moldova"),

    // Croatian
    HR_HR("hr-HR", "Croatian - Croatia"),

    // Slovenian
    SL_SI("sl-SI", "Slovenian - Slovenia"),

    // Bulgarian
    BG_BG("bg-BG", "Bulgarian - Bulgaria"),

    // Serbian variants
    SR_RS("sr-RS", "Serbian - Serbia"),
    SR_BA("sr-BA", "Serbian - Bosnia and Herzegovina"),
    SR_ME("sr-ME", "Serbian - Montenegro"),

    // Russian variants
    RU_RU("ru-RU", "Russian - Russia"),
    RU_UA("ru-UA", "Russian - Ukraine"),
    RU_BY("ru-BY", "Russian - Belarus"),
    RU_KZ("ru-KZ", "Russian - Kazakhstan"),
    RU_UZ("ru-UZ", "Russian - Uzbekistan"),

    // Ukrainian
    UK_UA("uk-UA", "Ukrainian - Ukraine"),

    // Belarusian
    BE_BY("be-BY", "Belarusian - Belarus"),

    // Lithuanian
    LT_LT("lt-LT", "Lithuanian - Lithuania"),

    // Latvian
    LV_LV("lv-LV", "Latvian - Latvia"),
    LV("lv", "Latvian - Generic"),

    // Estonian
    ET_EE("et-EE", "Estonian - Estonia"),

    // Greek variants
    EL_GR("el-GR", "Greek - Greece"),
    EL_CY("el-CY", "Greek - Cyprus"),

    // Turkish variants
    TR_TR("tr-TR", "Turkish - Turkey"),
    TR_CY("tr-CY", "Turkish - Cyprus"),

    // Hebrew
    HE_IL("he-IL", "Hebrew - Israel"),

    // Arabic variants
    AR_SA("ar-SA", "Arabic - Saudi Arabia"),
    AR_AE("ar-AE", "Arabic - United Arab Emirates"),
    AR_EG("ar-EG", "Arabic - Egypt"),
    AR_JO("ar-JO", "Arabic - Jordan"),
    AR_SY("ar-SY", "Arabic - Syria"),
    AR_LB("ar-LB", "Arabic - Lebanon"),
    AR_IL("ar-IL", "Arabic - Israel"),
    AR_PA("ar-PA", "Arabic - Palestine"),
    AR_IQ("ar-IQ", "Arabic - Iraq"),
    AR_KW("ar-KW", "Arabic - Kuwait"),
    AR_BH("ar-BH", "Arabic - Bahrain"),
    AR_QA("ar-QA", "Arabic - Qatar"),
    AR_OM("ar-OM", "Arabic - Oman"),
    AR_YE("ar-YE", "Arabic - Yemen"),
    AR_MO("ar-MO", "Arabic - Morocco"),
    AR_DZ("ar-DZ", "Arabic - Algeria"),
    AR_TN("ar-TN", "Arabic - Tunisia"),
    AR_LY("ar-LY", "Arabic - Libya"),
    AR_SD("ar-SD", "Arabic - Sudan"),

    // Persian/Farsi
    FA_IR("fa-IR", "Persian - Iran"),
    FA_AF("fa-AF", "Persian - Afghanistan"),

    // Japanese
    JA_JP("ja-JP", "Japanese - Japan"),

    // Korean variants
    KO_KR("ko-KR", "Korean - South Korea"),
    KO_KP("ko-KP", "Korean - North Korea"),

    // Chinese variants
    ZH_CN("zh-CN", "Chinese Simplified - China"),
    ZH_TW("zh-TW", "Chinese Traditional - Taiwan"),
    ZH_HK("zh-HK", "Chinese Traditional - Hong Kong"),
    ZH_MO("zh-MO", "Chinese Traditional - Macau"),
    ZH_SG("zh-SG", "Chinese Simplified - Singapore"),

    // Thai
    TH_TH("th-TH", "Thai - Thailand"),

    // Vietnamese
    VI_VN("vi-VN", "Vietnamese - Vietnam"),

    // Filipino/Tagalog
    TL_PH("tl-PH", "Tagalog - Philippines"),
    FIL_PH("fil-PH", "Filipino - Philippines"),

    // Indonesian variants
    ID_ID("id-ID", "Indonesian - Indonesia"),
    ID_BN("id-BN", "Indonesian - Brunei"),

    // Malaysian variants
    MS_MY("ms-MY", "Malay - Malaysia"),
    MS_BN("ms-BN", "Malay - Brunei"),
    MS_SG("ms-SG", "Malay - Singapore"),

    // Mongolian
    MN_MN("mn-MN", "Mongolian - Mongolia"),

    // Khmer
    KM_KH("km-KH", "Khmer - Cambodia"),

    // Lao
    LO_LA("lo-LA", "Lao - Laos"),

    // Burmese
    MY_MM("my-MM", "Burmese - Myanmar"),

    // Sinhala
    SI_LK("si-LK", "Sinhala - Sri Lanka"),

    // South Asian languages
    BN_BD("bn-BD", "Bengali - Bangladesh"),
    BN_IN("bn-IN", "Bengali - India"),
    HI_IN("hi-IN", "Hindi - India"),
    GU_IN("gu-IN", "Gujarati - India"),
    MR_IN("mr-IN", "Marathi - India"),
    TA_IN("ta-IN", "Tamil - India"),
    TA_LK("ta-LK", "Tamil - Sri Lanka"),
    TE_IN("te-IN", "Telugu - India"),
    KN_IN("kn-IN", "Kannada - India"),
    ML_IN("ml-IN", "Malayalam - India"),
    PA_IN("pa-IN", "Punjabi - India"),
    PA_PK("pa-PK", "Punjabi - Pakistan"),
    UR_PK("ur-PK", "Urdu - Pakistan"),
    UR_IN("ur-IN", "Urdu - India"),

    // Afghan languages
    PS_AF("ps-AF", "Pashto - Afghanistan"),
    PS_PK("ps-PK", "Pashto - Pakistan"),

    // African languages
    AM_ET("am-ET", "Amharic - Ethiopia"),
    SW_TZ("sw-TZ", "Swahili - Tanzania"),
    SW_KE("sw-KE", "Swahili - Kenya"),
    SW_UG("sw-UG", "Swahili - Uganda"),
    SW_RW("sw-RW", "Swahili - Rwanda"),
    SW_CD("sw-CD", "Swahili - Democratic Republic of Congo"),
    YO_NG("yo-NG", "Yoruba - Nigeria"),
    HA_NG("ha-NG", "Hausa - Nigeria"),
    HA_NE("ha-NE", "Hausa - Niger"),
    IG_NG("ig-NG", "Igbo - Nigeria"),
    XH_ZA("xh-ZA", "Xhosa - South Africa"),
    ZU_ZA("zu-ZA", "Zulu - South Africa"),
    AF_ZA("af-ZA", "Afrikaans - South Africa"),
    AF_NA("af-NA", "Afrikaans - Namibia"),

    // Other European languages
    IS_IS("is-IS", "Icelandic - Iceland"),
    SQ_AL("sq-AL", "Albanian - Albania"),
    SQ_XK("sq-XK", "Albanian - Kosovo"),
    SQ_MK("sq-MK", "Albanian - North Macedonia"),
    BS_BA("bs-BA", "Bosnian - Bosnia and Herzegovina"),
    MK_MK("mk-MK", "Macedonian - North Macedonia"),
    CA_ES("ca-ES", "Catalan - Spain"),
    CA_FR("ca-FR", "Catalan - France"),
    CA_AD("ca-AD", "Catalan - Andorra"),
    CA_IT("ca-IT", "Catalan - Italy"),
    GL_ES("gl-ES", "Galician - Spain"),
    EU_ES("eu-ES", "Basque - Spain"),
    BR_FR("br-FR", "Breton - France"),
    CY_GB("cy-GB", "Welsh - United Kingdom"),
    GA_IE("ga-IE", "Irish - Ireland"),
    MT_MT("mt-MT", "Maltese - Malta"),
    LB_LU("lb-LU", "Luxembourgish - Luxembourg");

    private final String code;
    private final String displayName;

    LanguageLocale(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Find LanguageLocale by code (case-insensitive)
     * @param code the locale code (e.g., "en-US", "sv-SE")
     * @return LanguageLocale enum value or null if not found
     */
    public static LanguageLocale fromCode(String code) {
        if (code == null) return null;
        for (LanguageLocale locale : values()) {
            if (locale.code.equalsIgnoreCase(code)) {
                return locale;
            }
        }
        return null;
    }

    /**
     * Map language code to appropriate country-specific variant
     * For example: "en" with country "US" becomes "en-US"
     * @param lang the base language code
     * @param country the ISO country code
     * @return mapped LanguageLocale or null if not found
     */
    public static LanguageLocale mapByCountry(String lang, String country) {
        if (lang == null || country == null) return null;
        
        String combined = lang.toLowerCase() + "-" + country.toUpperCase();
        return fromCode(combined);
    }
}
