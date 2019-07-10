package serialize.places

import com.algolia.search.model.places.Country
import com.algolia.search.model.places.Country.*
import com.algolia.search.serialize.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer


internal class TestCountries : TestSerializer<Country>(Country) {

    override val items = listOf(
        Afghanistan to JsonLiteral(KeyAfghanistan),
        AlandIslands to JsonLiteral(KeyAlandIslands),
        Albania to JsonLiteral(KeyAlbania),
        Algeria to JsonLiteral(KeyAlgeria),
        AmericanSamoa to JsonLiteral(KeyAmericanSamoa),
        Andorra to JsonLiteral(KeyAndorra),
        Angola to JsonLiteral(KeyAngola),
        Anguilla to JsonLiteral(KeyAnguilla),
        Antarctica to JsonLiteral(KeyAntarctica),
        AntiguaAndBarbuda to JsonLiteral(KeyAntiguaAndBarbuda),
        Argentina to JsonLiteral(KeyArgentina),
        Armenia to JsonLiteral(KeyArmenia),
        Aruba to JsonLiteral(KeyAruba),
        Australia to JsonLiteral(KeyAustralia),
        Austria to JsonLiteral(KeyAustria),
        Azerbaijan to JsonLiteral(KeyAzerbaijan),
        Bahamas to JsonLiteral(KeyBahamas),
        Bahrain to JsonLiteral(KeyBahrain),
        Bangladesh to JsonLiteral(KeyBangladesh),
        Barbados to JsonLiteral(KeyBarbados),
        Belarus to JsonLiteral(KeyBelarus),
        Belgium to JsonLiteral(KeyBelgium),
        Belize to JsonLiteral(KeyBelize),
        Benin to JsonLiteral(KeyBenin),
        Bermuda to JsonLiteral(KeyBermuda),
        Bhutan to JsonLiteral(KeyBhutan),
        Bolivia to JsonLiteral(KeyBolivia),
        CaribbeanNetherlands to JsonLiteral(KeyCaribbeanNetherlands),
        BosniaAndHerzegovina to JsonLiteral(KeyBosniaAndHerzegovina),
        Botswana to JsonLiteral(KeyBotswana),
        BouvetIsland to JsonLiteral(KeyBouvetIsland),
        Brazil to JsonLiteral(KeyBrazil),
        BritishIndianOceanTerritory to JsonLiteral(KeyBritishIndianOceanTerritory),
        BruneiDarussalam to JsonLiteral(KeyBruneiDarussalam),
        Bulgaria to JsonLiteral(KeyBulgaria),
        BurkinaFaso to JsonLiteral(KeyBurkinaFaso),
        Burundi to JsonLiteral(KeyBurundi),
        CaboVerde to JsonLiteral(KeyCaboVerde),
        Cambodia to JsonLiteral(KeyCambodia),
        Cameroon to JsonLiteral(KeyCameroon),
        Canada to JsonLiteral(KeyCanada),
        CaymanIslands to JsonLiteral(KeyCaymanIslands),
        CentralAfricanRepublic to JsonLiteral(KeyCentralAfricanRepublic),
        Chad to JsonLiteral(KeyChad),
        Chile to JsonLiteral(KeyChile),
        China to JsonLiteral(KeyChina),
        ChristmasIsland to JsonLiteral(KeyChristmasIsland),
        CocosIslands to JsonLiteral(KeyCocosIslands),
        Colombia to JsonLiteral(KeyColombia),
        Comoros to JsonLiteral(KeyComoros),
        RepublicOfTheCongo to JsonLiteral(KeyRepublicOfTheCongo),
        DemocraticRepublicOfTheCongo to JsonLiteral(KeyDemocraticRepublicOfTheCongo),
        CookIslands to JsonLiteral(KeyCookIslands),
        CostaRica to JsonLiteral(KeyCostaRica),
        IvoryCoast to JsonLiteral(KeyIvoryCoast),
        Croatia to JsonLiteral(KeyCroatia),
        Cuba to JsonLiteral(KeyCuba),
        Curacao to JsonLiteral(KeyCuracao),
        Cyprus to JsonLiteral(KeyCyprus),
        CzechRepublic to JsonLiteral(KeyCzechRepublic),
        Denmark to JsonLiteral(KeyDenmark),
        Djibouti to JsonLiteral(KeyDjibouti),
        Dominica to JsonLiteral(KeyDominica),
        DominicanRepublic to JsonLiteral(KeyDominicanRepublic),
        Ecuador to JsonLiteral(KeyEcuador),
        Egypt to JsonLiteral(KeyEgypt),
        ElSalvador to JsonLiteral(KeyElSalvador),
        EquatorialGuinea to JsonLiteral(KeyEquatorialGuinea),
        Eritrea to JsonLiteral(KeyEritrea),
        Estonia to JsonLiteral(KeyEstonia),
        Eswatini to JsonLiteral(KeyEswatini),
        Ethiopia to JsonLiteral(KeyEthiopia),
        FalklandIslands to JsonLiteral(KeyFalklandIslands),
        FaroeIslands to JsonLiteral(KeyFaroeIslands),
        Fiji to JsonLiteral(KeyFiji),
        Finland to JsonLiteral(KeyFinland),
        France to JsonLiteral(KeyFrance),
        FrenchGuiana to JsonLiteral(KeyFrenchGuiana),
        FrenchPolynesia to JsonLiteral(KeyFrenchPolynesia),
        FrenchSouthernAndAntarcticLands to JsonLiteral(KeyFrenchSouthernAndAntarcticLands),
        Gabon to JsonLiteral(KeyGabon),
        Gambia to JsonLiteral(KeyGambia),
        Georgia to JsonLiteral(KeyGeorgia),
        Germany to JsonLiteral(KeyGermany),
        Ghana to JsonLiteral(KeyGhana),
        Gibraltar to JsonLiteral(KeyGibraltar),
        Greece to JsonLiteral(KeyGreece),
        Greenland to JsonLiteral(KeyGreenland),
        Grenada to JsonLiteral(KeyGrenada),
        Guadeloupe to JsonLiteral(KeyGuadeloupe),
        Guam to JsonLiteral(KeyGuam),
        Guatemala to JsonLiteral(KeyGuatemala),
        BailiwickOfGuernsey to JsonLiteral(KeyBailiwickOfGuernsey),
        Guinea to JsonLiteral(KeyGuinea),
        GuineaBissau to JsonLiteral(KeyGuineaBissau),
        Guyana to JsonLiteral(KeyGuyana),
        Haiti to JsonLiteral(KeyHaiti),
        HeardIslandAndMcDonaldIslands to JsonLiteral(KeyHeardIslandAndMcDonaldIslands),
        VaticanCity to JsonLiteral(KeyVaticanCity),
        Honduras to JsonLiteral(KeyHonduras),
        HongKong to JsonLiteral(KeyHongKong),
        Hungary to JsonLiteral(KeyHungary),
        Iceland to JsonLiteral(KeyIceland),
        India to JsonLiteral(KeyIndia),
        Indonesia to JsonLiteral(KeyIndonesia),
        Iran to JsonLiteral(KeyIran),
        Iraq to JsonLiteral(KeyIraq),
        Ireland to JsonLiteral(KeyIreland),
        IsleOfMan to JsonLiteral(KeyIsleOfMan),
        Israel to JsonLiteral(KeyIsrael),
        Italy to JsonLiteral(KeyItaly),
        Jamaica to JsonLiteral(KeyJamaica),
        Japan to JsonLiteral(KeyJapan),
        Jersey to JsonLiteral(KeyJersey),
        Jordan to JsonLiteral(KeyJordan),
        Kazakhstan to JsonLiteral(KeyKazakhstan),
        Kenya to JsonLiteral(KeyKenya),
        Kiribati to JsonLiteral(KeyKiribati),
        NorthKorea to JsonLiteral(KeyNorthKorea),
        SouthKorea to JsonLiteral(KeySouthKorea),
        Kuwait to JsonLiteral(KeyKuwait),
        Kyrgyzstan to JsonLiteral(KeyKyrgyzstan),
        Laos to JsonLiteral(KeyLaos),
        Latvia to JsonLiteral(KeyLatvia),
        Lebanon to JsonLiteral(KeyLebanon),
        Lesotho to JsonLiteral(KeyLesotho),
        Liberia to JsonLiteral(KeyLiberia),
        Libya to JsonLiteral(KeyLibya),
        Liechtenstein to JsonLiteral(KeyLiechtenstein),
        Lithuania to JsonLiteral(KeyLithuania),
        Luxembourg to JsonLiteral(KeyLuxembourg),
        Macau to JsonLiteral(KeyMacau),
        Madagascar to JsonLiteral(KeyMadagascar),
        Malawi to JsonLiteral(KeyMalawi),
        Malaysia to JsonLiteral(KeyMalaysia),
        Maldives to JsonLiteral(KeyMaldives),
        Mali to JsonLiteral(KeyMali),
        Malta to JsonLiteral(KeyMalta),
        MarshallIslands to JsonLiteral(KeyMarshallIslands),
        Martinique to JsonLiteral(KeyMartinique),
        Mauritania to JsonLiteral(KeyMauritania),
        Mauritius to JsonLiteral(KeyMauritius),
        Mayotte to JsonLiteral(KeyMayotte),
        Mexico to JsonLiteral(KeyMexico),
        Micronesia to JsonLiteral(KeyMicronesia),
        Moldova to JsonLiteral(KeyMoldova),
        Monaco to JsonLiteral(KeyMonaco),
        Mongolia to JsonLiteral(KeyMongolia),
        Montenegro to JsonLiteral(KeyMontenegro),
        Montserrat to JsonLiteral(KeyMontserrat),
        Morocco to JsonLiteral(KeyMorocco),
        Mozambique to JsonLiteral(KeyMozambique),
        Myanmar to JsonLiteral(KeyMyanmar),
        Namibia to JsonLiteral(KeyNamibia),
        Nauru to JsonLiteral(KeyNauru),
        Nepal to JsonLiteral(KeyNepal),
        Netherlands to JsonLiteral(KeyNetherlands),
        NewCaledonia to JsonLiteral(KeyNewCaledonia),
        NewZealand to JsonLiteral(KeyNewZealand),
        Nicaragua to JsonLiteral(KeyNicaragua),
        Niger to JsonLiteral(KeyNiger),
        Nigeria to JsonLiteral(KeyNigeria),
        Niue to JsonLiteral(KeyNiue),
        NorfolkIsland to JsonLiteral(KeyNorfolkIsland),
        NorthMacedonia to JsonLiteral(KeyNorthMacedonia),
        NorthernMarianaIslands to JsonLiteral(KeyNorthernMarianaIslands),
        Norway to JsonLiteral(KeyNorway),
        Oman to JsonLiteral(KeyOman),
        Pakistan to JsonLiteral(KeyPakistan),
        Palau to JsonLiteral(KeyPalau),
        Palestine to JsonLiteral(KeyPalestine),
        Panama to JsonLiteral(KeyPanama),
        PapuaNewGuinea to JsonLiteral(KeyPapuaNewGuinea),
        Paraguay to JsonLiteral(KeyParaguay),
        Peru to JsonLiteral(KeyPeru),
        Philippines to JsonLiteral(KeyPhilippines),
        PitcairnIslands to JsonLiteral(KeyPitcairnIslands),
        Poland to JsonLiteral(KeyPoland),
        Portugal to JsonLiteral(KeyPortugal),
        PuertoRico to JsonLiteral(KeyPuertoRico),
        Qatar to JsonLiteral(KeyQatar),
        Reunion to JsonLiteral(KeyReunion),
        Romania to JsonLiteral(KeyRomania),
        Russia to JsonLiteral(KeyRussia),
        Rwanda to JsonLiteral(KeyRwanda),
        SaintBarthelemy to JsonLiteral(KeySaintBarthelemy),
        SaintHelena to JsonLiteral(KeySaintHelena),
        SaintKittsAndNevis to JsonLiteral(KeySaintKittsAndNevis),
        SaintLucia to JsonLiteral(KeySaintLucia),
        SaintMartin to JsonLiteral(KeySaintMartin),
        SaintPierreAndMiquelon to JsonLiteral(KeySaintPierreAndMiquelon),
        SaintVincentAndTheGrenadines to JsonLiteral(KeySaintVincentAndTheGrenadines),
        Samoa to JsonLiteral(KeySamoa),
        SanMarino to JsonLiteral(KeySanMarino),
        SaoTomeAndPrincipe to JsonLiteral(KeySaoTomeAndPrincipe),
        SaudiArabia to JsonLiteral(KeySaudiArabia),
        Senegal to JsonLiteral(KeySenegal),
        Serbia to JsonLiteral(KeySerbia),
        Seychelles to JsonLiteral(KeySeychelles),
        SierraLeone to JsonLiteral(KeySierraLeone),
        Singapore to JsonLiteral(KeySingapore),
        SintMaarten to JsonLiteral(KeySintMaarten),
        Slovakia to JsonLiteral(KeySlovakia),
        Slovenia to JsonLiteral(KeySlovenia),
        SolomonIslands to JsonLiteral(KeySolomonIslands),
        Somalia to JsonLiteral(KeySomalia),
        SouthAfrica to JsonLiteral(KeySouthAfrica),
        SouthGeorgiaAndTheSouthSandwichIslands to JsonLiteral(KeySouthGeorgiaAndTheSouthSandwichIslands),
        SouthSudan to JsonLiteral(KeySouthSudan),
        Spain to JsonLiteral(KeySpain),
        SriLanka to JsonLiteral(KeySriLanka),
        Sudan to JsonLiteral(KeySudan),
        Suriname to JsonLiteral(KeySuriname),
        SvalbardAndJanMayen to JsonLiteral(KeySvalbardAndJanMayen),
        Sweden to JsonLiteral(KeySweden),
        Switzerland to JsonLiteral(KeySwitzerland),
        Syria to JsonLiteral(KeySyria),
        Taiwan to JsonLiteral(KeyTaiwan),
        Tajikistan to JsonLiteral(KeyTajikistan),
        Tanzania to JsonLiteral(KeyTanzania),
        Thailand to JsonLiteral(KeyThailand),
        TimorLeste to JsonLiteral(KeyTimorLeste),
        Togo to JsonLiteral(KeyTogo),
        Tokelau to JsonLiteral(KeyTokelau),
        Tonga to JsonLiteral(KeyTonga),
        TrinidadAndTobago to JsonLiteral(KeyTrinidadAndTobago),
        Tunisia to JsonLiteral(KeyTunisia),
        Turkey to JsonLiteral(KeyTurkey),
        Turkmenistan to JsonLiteral(KeyTurkmenistan),
        TurksAndCaicosIslands to JsonLiteral(KeyTurksAndCaicosIslands),
        Tuvalu to JsonLiteral(KeyTuvalu),
        Uganda to JsonLiteral(KeyUganda),
        Ukraine to JsonLiteral(KeyUkraine),
        UnitedArabEmirates to JsonLiteral(KeyUnitedArabEmirates),
        UnitedKingdom to JsonLiteral(KeyUnitedKingdom),
        UnitedStates to JsonLiteral(KeyUnitedStates),
        UnitedStatesMinorOutlyingIslands to JsonLiteral(KeyUnitedStatesMinorOutlyingIslands),
        Uruguay to JsonLiteral(KeyUruguay),
        Uzbekistan to JsonLiteral(KeyUzbekistan),
        Vanuatu to JsonLiteral(KeyVanuatu),
        Venezuela to JsonLiteral(KeyVenezuela),
        Vietnam to JsonLiteral(KeyVietnam),
        VirginIslandsGB to JsonLiteral(KeyVirginIslandsGB),
        VirginIslandsUS to JsonLiteral(KeyVirginIslandsUS),
        WallisAndFutuna to JsonLiteral(KeyWallisAndFutuna),
        WesternSahara to JsonLiteral(KeyWesternSahara),
        Yemen to JsonLiteral(KeyYemen),
        Zambia to JsonLiteral(KeyZambia),
        Zimbabwe to JsonLiteral(KeyZimbabwe)
    )
}