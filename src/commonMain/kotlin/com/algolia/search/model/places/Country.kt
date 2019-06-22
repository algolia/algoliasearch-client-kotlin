package com.algolia.search.model.places

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


/**
 * List of countries with associated country code.
 * You can pass two letters country codes (ISO 3166-1) using the [Other] class, but they need to be lower-cased.
 */
@Serializable(Country.Companion::class)
sealed class Country(override val raw: String) : Raw<String> {

    public object Afghanistan : Country(KeyAfghanistan)
    public object AlandIslands : Country(KeyAlandIslands)
    public object Albania : Country(KeyAlbania)
    public object Algeria : Country(KeyAlgeria)
    public object AmericanSamoa : Country(KeyAmericanSamoa)
    public object Andorra : Country(KeyAndorra)
    public object Angola : Country(KeyAngola)
    public object Anguilla : Country(KeyAnguilla)
    public object Antarctica : Country(KeyAntarctica)
    public object AntiguaAndBarbuda : Country(KeyAntiguaAndBarbuda)
    public object Argentina : Country(KeyArgentina)
    public object Armenia : Country(KeyArmenia)
    public object Aruba : Country(KeyAruba)
    public object Australia : Country(KeyAustralia)
    public object Austria : Country(KeyAustria)
    public object Azerbaijan : Country(KeyAzerbaijan)
    public object Bahamas : Country(KeyBahamas)
    public object Bahrain : Country(KeyBahrain)
    public object Bangladesh : Country(KeyBangladesh)
    public object Barbados : Country(KeyBarbados)
    public object Belarus : Country(KeyBelarus)
    public object Belgium : Country(KeyBelgium)
    public object Belize : Country(KeyBelize)
    public object Benin : Country(KeyBenin)
    public object Bermuda : Country(KeyBermuda)
    public object Bhutan : Country(KeyBhutan)
    public object Bolivia : Country(KeyBolivia)
    public object CaribbeanNetherlands : Country(KeyCaribbeanNetherlands)
    public object BosniaAndHerzegovina : Country(KeyBosniaAndHerzegovina)
    public object Botswana : Country(KeyBotswana)
    public object BouvetIsland : Country(KeyBouvetIsland)
    public object Brazil : Country(KeyBrazil)
    public object BritishIndianOceanTerritory : Country(KeyBritishIndianOceanTerritory)
    public object BruneiDarussalam : Country(KeyBruneiDarussalam)
    public object Bulgaria : Country(KeyBulgaria)
    public object BurkinaFaso : Country(KeyBurkinaFaso)
    public object Burundi : Country(KeyBurundi)
    public object CaboVerde : Country(KeyCaboVerde)
    public object Cambodia : Country(KeyCambodia)
    public object Cameroon : Country(KeyCameroon)
    public object Canada : Country(KeyCanada)
    public object CaymanIslands : Country(KeyCaymanIslands)
    public object CentralAfricanRepublic : Country(KeyCentralAfricanRepublic)
    public object Chad : Country(KeyChad)
    public object Chile : Country(KeyChile)
    public object China : Country(KeyChina)
    public object ChristmasIsland : Country(KeyChristmasIsland)
    public object CocosIslands : Country(KeyCocosIslands)
    public object Colombia : Country(KeyColombia)
    public object Comoros : Country(KeyComoros)
    public object RepublicOfTheCongo : Country(KeyRepublicOfTheCongo)
    public object DemocraticRepublicOfTheCongo : Country(KeyDemocraticRepublicOfTheCongo)
    public object CookIslands : Country(KeyCookIslands)
    public object CostaRica : Country(KeyCostaRica)
    public object IvoryCoast : Country(KeyIvoryCoast)
    public object Croatia : Country(KeyCroatia)
    public object Cuba : Country(KeyCuba)
    public object Curacao : Country(KeyCuracao)
    public object Cyprus : Country(KeyCyprus)
    public object CzechRepublic : Country(KeyCzechRepublic)
    public object Denmark : Country(KeyDenmark)
    public object Djibouti : Country(KeyDjibouti)
    public object Dominica : Country(KeyDominica)
    public object DominicanRepublic : Country(KeyDominicanRepublic)
    public object Ecuador : Country(KeyEcuador)
    public object Egypt : Country(KeyEgypt)
    public object ElSalvador : Country(KeyElSalvador)
    public object EquatorialGuinea : Country(KeyEquatorialGuinea)
    public object Eritrea : Country(KeyEritrea)
    public object Estonia : Country(KeyEstonia)
    public object Eswatini : Country(KeyEswatini)
    public object Ethiopia : Country(KeyEthiopia)
    public object FalklandIslands : Country(KeyFalklandIslands)
    public object FaroeIslands : Country(KeyFaroeIslands)
    public object Fiji : Country(KeyFiji)
    public object Finland : Country(KeyFinland)
    public object France : Country(KeyFrance)
    public object FrenchGuiana : Country(KeyFrenchGuiana)
    public object FrenchPolynesia : Country(KeyFrenchPolynesia)
    public object FrenchSouthernAndAntarcticLands : Country(KeyFrenchSouthernAndAntarcticLands)
    public object Gabon : Country(KeyGabon)
    public object Gambia : Country(KeyGambia)
    public object Georgia : Country(KeyGeorgia)
    public object Germany : Country(KeyGermany)
    public object Ghana : Country(KeyGhana)
    public object Gibraltar : Country(KeyGibraltar)
    public object Greece : Country(KeyGreece)
    public object Greenland : Country(KeyGreenland)
    public object Grenada : Country(KeyGrenada)
    public object Guadeloupe : Country(KeyGuadeloupe)
    public object Guam : Country(KeyGuam)
    public object Guatemala : Country(KeyGuatemala)
    public object BailiwickOfGuernsey : Country(KeyBailiwickOfGuernsey)
    public object Guinea : Country(KeyGuinea)
    public object GuineaBissau : Country(KeyGuineaBissau)
    public object Guyana : Country(KeyGuyana)
    public object Haiti : Country(KeyHaiti)
    public object HeardIslandAndMcDonaldIslands : Country(KeyHeardIslandAndMcDonaldIslands)
    public object VaticanCity : Country(KeyVaticanCity)
    public object Honduras : Country(KeyHonduras)
    public object HongKong : Country(KeyHongKong)
    public object Hungary : Country(KeyHungary)
    public object Iceland : Country(KeyIceland)
    public object India : Country(KeyIndia)
    public object Indonesia : Country(KeyIndonesia)
    public object Iran : Country(KeyIran)
    public object Iraq : Country(KeyIraq)
    public object Ireland : Country(KeyIreland)
    public object IsleOfMan : Country(KeyIsleOfMan)
    public object Israel : Country(KeyIsrael)
    public object Italy : Country(KeyItaly)
    public object Jamaica : Country(KeyJamaica)
    public object Japan : Country(KeyJapan)
    public object Jersey : Country(KeyJersey)
    public object Jordan : Country(KeyJordan)
    public object Kazakhstan : Country(KeyKazakhstan)
    public object Kenya : Country(KeyKenya)
    public object Kiribati : Country(KeyKiribati)
    public object NorthKorea : Country(KeyNorthKorea)
    public object SouthKorea : Country(KeySouthKorea)
    public object Kuwait : Country(KeyKuwait)
    public object Kyrgyzstan : Country(KeyKyrgyzstan)
    public object Laos : Country(KeyLaos)
    public object Latvia : Country(KeyLatvia)
    public object Lebanon : Country(KeyLebanon)
    public object Lesotho : Country(KeyLesotho)
    public object Liberia : Country(KeyLiberia)
    public object Libya : Country(KeyLibya)
    public object Liechtenstein : Country(KeyLiechtenstein)
    public object Lithuania : Country(KeyLithuania)
    public object Luxembourg : Country(KeyLuxembourg)
    public object Macau : Country(KeyMacau)
    public object Madagascar : Country(KeyMadagascar)
    public object Malawi : Country(KeyMalawi)
    public object Malaysia : Country(KeyMalaysia)
    public object Maldives : Country(KeyMaldives)
    public object Mali : Country(KeyMali)
    public object Malta : Country(KeyMalta)
    public object MarshallIslands : Country(KeyMarshallIslands)
    public object Martinique : Country(KeyMartinique)
    public object Mauritania : Country(KeyMauritania)
    public object Mauritius : Country(KeyMauritius)
    public object Mayotte : Country(KeyMayotte)
    public object Mexico : Country(KeyMexico)
    public object Micronesia : Country(KeyMicronesia)
    public object Moldova : Country(KeyMoldova)
    public object Monaco : Country(KeyMonaco)
    public object Mongolia : Country(KeyMongolia)
    public object Montenegro : Country(KeyMontenegro)
    public object Montserrat : Country(KeyMontserrat)
    public object Morocco : Country(KeyMorocco)
    public object Mozambique : Country(KeyMozambique)
    public object Myanmar : Country(KeyMyanmar)
    public object Namibia : Country(KeyNamibia)
    public object Nauru : Country(KeyNauru)
    public object Nepal : Country(KeyNepal)
    public object Netherlands : Country(KeyNetherlands)
    public object NewCaledonia : Country(KeyNewCaledonia)
    public object NewZealand : Country(KeyNewZealand)
    public object Nicaragua : Country(KeyNicaragua)
    public object Niger : Country(KeyNiger)
    public object Nigeria : Country(KeyNigeria)
    public object Niue : Country(KeyNiue)
    public object NorfolkIsland : Country(KeyNorfolkIsland)
    public object NorthMacedonia : Country(KeyNorthMacedonia)
    public object NorthernMarianaIslands : Country(KeyNorthernMarianaIslands)
    public object Norway : Country(KeyNorway)
    public object Oman : Country(KeyOman)
    public object Pakistan : Country(KeyPakistan)
    public object Palau : Country(KeyPalau)
    public object Palestine : Country(KeyPalestine)
    public object Panama : Country(KeyPanama)
    public object PapuaNewGuinea : Country(KeyPapuaNewGuinea)
    public object Paraguay : Country(KeyParaguay)
    public object Peru : Country(KeyPeru)
    public object Philippines : Country(KeyPhilippines)
    public object PitcairnIslands : Country(KeyPitcairnIslands)
    public object Poland : Country(KeyPoland)
    public object Portugal : Country(KeyPortugal)
    public object PuertoRico : Country(KeyPuertoRico)
    public object Qatar : Country(KeyQatar)
    public object Reunion : Country(KeyReunion)
    public object Romania : Country(KeyRomania)
    public object Russia : Country(KeyRussia)
    public object Rwanda : Country(KeyRwanda)
    public object SaintBarthelemy : Country(KeySaintBarthelemy)
    public object SaintHelena : Country(KeySaintHelena)
    public object SaintKittsAndNevis : Country(KeySaintKittsAndNevis)
    public object SaintLucia : Country(KeySaintLucia)
    public object SaintMartin : Country(KeySaintMartin)
    public object SaintPierreAndMiquelon : Country(KeySaintPierreAndMiquelon)
    public object SaintVincentAndTheGrenadines : Country(KeySaintVincentAndTheGrenadines)
    public object Samoa : Country(KeySamoa)
    public object SanMarino : Country(KeySanMarino)
    public object SaoTomeAndPrincipe : Country(KeySaoTomeAndPrincipe)
    public object SaudiArabia : Country(KeySaudiArabia)
    public object Senegal : Country(KeySenegal)
    public object Serbia : Country(KeySerbia)
    public object Seychelles : Country(KeySeychelles)
    public object SierraLeone : Country(KeySierraLeone)
    public object Singapore : Country(KeySingapore)
    public object SintMaarten : Country(KeySintMaarten)
    public object Slovakia : Country(KeySlovakia)
    public object Slovenia : Country(KeySlovenia)
    public object SolomonIslands : Country(KeySolomonIslands)
    public object Somalia : Country(KeySomalia)
    public object SouthAfrica : Country(KeySouthAfrica)
    public object SouthGeorgiaAndTheSouthSandwichIslands : Country(KeySouthGeorgiaAndTheSouthSandwichIslands)
    public object SouthSudan : Country(KeySouthSudan)
    public object Spain : Country(KeySpain)
    public object SriLanka : Country(KeySriLanka)
    public object Sudan : Country(KeySudan)
    public object Suriname : Country(KeySuriname)
    public object SvalbardAndJanMayen : Country(KeySvalbardAndJanMayen)
    public object Sweden : Country(KeySweden)
    public object Switzerland : Country(KeySwitzerland)
    public object Syria : Country(KeySyria)
    public object Taiwan : Country(KeyTaiwan)
    public object Tajikistan : Country(KeyTajikistan)
    public object Tanzania : Country(KeyTanzania)
    public object Thailand : Country(KeyThailand)
    public object TimorLeste : Country(KeyTimorLeste)
    public object Togo : Country(KeyTogo)
    public object Tokelau : Country(KeyTokelau)
    public object Tonga : Country(KeyTonga)
    public object TrinidadAndTobago : Country(KeyTrinidadAndTobago)
    public object Tunisia : Country(KeyTunisia)
    public object Turkey : Country(KeyTurkey)
    public object Turkmenistan : Country(KeyTurkmenistan)
    public object TurksAndCaicosIslands : Country(KeyTurksAndCaicosIslands)
    public object Tuvalu : Country(KeyTuvalu)
    public object Uganda : Country(KeyUganda)
    public object Ukraine : Country(KeyUkraine)
    public object UnitedArabEmirates : Country(KeyUnitedArabEmirates)
    public object UnitedKingdom : Country(KeyUnitedKingdom)
    public object UnitedStates : Country(KeyUnitedStates)
    public object UnitedStatesMinorOutlyingIslands : Country(KeyUnitedStatesMinorOutlyingIslands)
    public object Uruguay : Country(KeyUruguay)
    public object Uzbekistan : Country(KeyUzbekistan)
    public object Vanuatu : Country(KeyVanuatu)
    public object Venezuela : Country(KeyVenezuela)
    public object Vietnam : Country(KeyVietnam)
    public object VirginIslandsGB : Country(KeyVirginIslandsGB)
    public object VirginIslandsUS : Country(KeyVirginIslandsUS)
    public object WallisAndFutuna : Country(KeyWallisAndFutuna)
    public object WesternSahara : Country(KeyWesternSahara)
    public object Yemen : Country(KeyYemen)
    public object Zambia : Country(KeyZambia)
    public object Zimbabwe : Country(KeyZimbabwe)

    public data class Other(override val raw: String) : Country(raw)

    companion object : KSerializer<Country> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Country) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Country {
            return when (val string = serializer.deserialize(decoder)) {
                KeyAfghanistan -> Afghanistan
                KeyAlandIslands -> AlandIslands
                KeyAlbania -> Albania
                KeyAlgeria -> Algeria
                KeyAmericanSamoa -> AmericanSamoa
                KeyAndorra -> Andorra
                KeyAngola -> Angola
                KeyAnguilla -> Anguilla
                KeyAntarctica -> Antarctica
                KeyAntiguaAndBarbuda -> AntiguaAndBarbuda
                KeyArgentina -> Argentina
                KeyArmenia -> Armenia
                KeyAruba -> Aruba
                KeyAustralia -> Australia
                KeyAustria -> Austria
                KeyAzerbaijan -> Azerbaijan
                KeyBahamas -> Bahamas
                KeyBahrain -> Bahrain
                KeyBangladesh -> Bangladesh
                KeyBarbados -> Barbados
                KeyBelarus -> Belarus
                KeyBelgium -> Belgium
                KeyBelize -> Belize
                KeyBenin -> Benin
                KeyBermuda -> Bermuda
                KeyBhutan -> Bhutan
                KeyBolivia -> Bolivia
                KeyCaribbeanNetherlands -> CaribbeanNetherlands
                KeyBosniaAndHerzegovina -> BosniaAndHerzegovina
                KeyBotswana -> Botswana
                KeyBouvetIsland -> BouvetIsland
                KeyBrazil -> Brazil
                KeyBritishIndianOceanTerritory -> BritishIndianOceanTerritory
                KeyBruneiDarussalam -> BruneiDarussalam
                KeyBulgaria -> Bulgaria
                KeyBurkinaFaso -> BurkinaFaso
                KeyBurundi -> Burundi
                KeyCaboVerde -> CaboVerde
                KeyCambodia -> Cambodia
                KeyCameroon -> Cameroon
                KeyCanada -> Canada
                KeyCaymanIslands -> CaymanIslands
                KeyCentralAfricanRepublic -> CentralAfricanRepublic
                KeyChad -> Chad
                KeyChile -> Chile
                KeyChina -> China
                KeyChristmasIsland -> ChristmasIsland
                KeyCocosIslands -> CocosIslands
                KeyColombia -> Colombia
                KeyComoros -> Comoros
                KeyRepublicOfTheCongo -> RepublicOfTheCongo
                KeyDemocraticRepublicOfTheCongo -> DemocraticRepublicOfTheCongo
                KeyCookIslands -> CookIslands
                KeyCostaRica -> CostaRica
                KeyIvoryCoast -> IvoryCoast
                KeyCroatia -> Croatia
                KeyCuba -> Cuba
                KeyCuracao -> Curacao
                KeyCyprus -> Cyprus
                KeyCzechRepublic -> CzechRepublic
                KeyDenmark -> Denmark
                KeyDjibouti -> Djibouti
                KeyDominica -> Dominica
                KeyDominicanRepublic -> DominicanRepublic
                KeyEcuador -> Ecuador
                KeyEgypt -> Egypt
                KeyElSalvador -> ElSalvador
                KeyEquatorialGuinea -> EquatorialGuinea
                KeyEritrea -> Eritrea
                KeyEstonia -> Estonia
                KeyEswatini -> Eswatini
                KeyEthiopia -> Ethiopia
                KeyFalklandIslands -> FalklandIslands
                KeyFaroeIslands -> FaroeIslands
                KeyFiji -> Fiji
                KeyFinland -> Finland
                KeyFrance -> France
                KeyFrenchGuiana -> FrenchGuiana
                KeyFrenchPolynesia -> FrenchPolynesia
                KeyFrenchSouthernAndAntarcticLands -> FrenchSouthernAndAntarcticLands
                KeyGabon -> Gabon
                KeyGambia -> Gambia
                KeyGeorgia -> Georgia
                KeyGermany -> Germany
                KeyGhana -> Ghana
                KeyGibraltar -> Gibraltar
                KeyGreece -> Greece
                KeyGreenland -> Greenland
                KeyGrenada -> Grenada
                KeyGuadeloupe -> Guadeloupe
                KeyGuam -> Guam
                KeyGuatemala -> Guatemala
                KeyBailiwickOfGuernsey -> BailiwickOfGuernsey
                KeyGuinea -> Guinea
                KeyGuineaBissau -> GuineaBissau
                KeyGuyana -> Guyana
                KeyHaiti -> Haiti
                KeyHeardIslandAndMcDonaldIslands -> HeardIslandAndMcDonaldIslands
                KeyVaticanCity -> VaticanCity
                KeyHonduras -> Honduras
                KeyHongKong -> HongKong
                KeyHungary -> Hungary
                KeyIceland -> Iceland
                KeyIndia -> India
                KeyIndonesia -> Indonesia
                KeyIran -> Iran
                KeyIraq -> Iraq
                KeyIreland -> Ireland
                KeyIsleOfMan -> IsleOfMan
                KeyIsrael -> Israel
                KeyItaly -> Italy
                KeyJamaica -> Jamaica
                KeyJapan -> Japan
                KeyJersey -> Jersey
                KeyJordan -> Jordan
                KeyKazakhstan -> Kazakhstan
                KeyKenya -> Kenya
                KeyKiribati -> Kiribati
                KeyNorthKorea -> NorthKorea
                KeySouthKorea -> SouthKorea
                KeyKuwait -> Kuwait
                KeyKyrgyzstan -> Kyrgyzstan
                KeyLaos -> Laos
                KeyLatvia -> Latvia
                KeyLebanon -> Lebanon
                KeyLesotho -> Lesotho
                KeyLiberia -> Liberia
                KeyLibya -> Libya
                KeyLiechtenstein -> Liechtenstein
                KeyLithuania -> Lithuania
                KeyLuxembourg -> Luxembourg
                KeyMacau -> Macau
                KeyMadagascar -> Madagascar
                KeyMalawi -> Malawi
                KeyMalaysia -> Malaysia
                KeyMaldives -> Maldives
                KeyMali -> Mali
                KeyMalta -> Malta
                KeyMarshallIslands -> MarshallIslands
                KeyMartinique -> Martinique
                KeyMauritania -> Mauritania
                KeyMauritius -> Mauritius
                KeyMayotte -> Mayotte
                KeyMexico -> Mexico
                KeyMicronesia -> Micronesia
                KeyMoldova -> Moldova
                KeyMonaco -> Monaco
                KeyMongolia -> Mongolia
                KeyMontenegro -> Montenegro
                KeyMontserrat -> Montserrat
                KeyMorocco -> Morocco
                KeyMozambique -> Mozambique
                KeyMyanmar -> Myanmar
                KeyNamibia -> Namibia
                KeyNauru -> Nauru
                KeyNepal -> Nepal
                KeyNetherlands -> Netherlands
                KeyNewCaledonia -> NewCaledonia
                KeyNewZealand -> NewZealand
                KeyNicaragua -> Nicaragua
                KeyNiger -> Niger
                KeyNigeria -> Nigeria
                KeyNiue -> Niue
                KeyNorfolkIsland -> NorfolkIsland
                KeyNorthMacedonia -> NorthMacedonia
                KeyNorthernMarianaIslands -> NorthernMarianaIslands
                KeyNorway -> Norway
                KeyOman -> Oman
                KeyPakistan -> Pakistan
                KeyPalau -> Palau
                KeyPalestine -> Palestine
                KeyPanama -> Panama
                KeyPapuaNewGuinea -> PapuaNewGuinea
                KeyParaguay -> Paraguay
                KeyPeru -> Peru
                KeyPhilippines -> Philippines
                KeyPitcairnIslands -> PitcairnIslands
                KeyPoland -> Poland
                KeyPortugal -> Portugal
                KeyPuertoRico -> PuertoRico
                KeyQatar -> Qatar
                KeyReunion -> Reunion
                KeyRomania -> Romania
                KeyRussia -> Russia
                KeyRwanda -> Rwanda
                KeySaintBarthelemy -> SaintBarthelemy
                KeySaintHelena -> SaintHelena
                KeySaintKittsAndNevis -> SaintKittsAndNevis
                KeySaintLucia -> SaintLucia
                KeySaintMartin -> SaintMartin
                KeySaintPierreAndMiquelon -> SaintPierreAndMiquelon
                KeySaintVincentAndTheGrenadines -> SaintVincentAndTheGrenadines
                KeySamoa -> Samoa
                KeySanMarino -> SanMarino
                KeySaoTomeAndPrincipe -> SaoTomeAndPrincipe
                KeySaudiArabia -> SaudiArabia
                KeySenegal -> Senegal
                KeySerbia -> Serbia
                KeySeychelles -> Seychelles
                KeySierraLeone -> SierraLeone
                KeySingapore -> Singapore
                KeySintMaarten -> SintMaarten
                KeySlovakia -> Slovakia
                KeySlovenia -> Slovenia
                KeySolomonIslands -> SolomonIslands
                KeySomalia -> Somalia
                KeySouthAfrica -> SouthAfrica
                KeySouthGeorgiaAndTheSouthSandwichIslands -> SouthGeorgiaAndTheSouthSandwichIslands
                KeySouthSudan -> SouthSudan
                KeySpain -> Spain
                KeySriLanka -> SriLanka
                KeySudan -> Sudan
                KeySuriname -> Suriname
                KeySvalbardAndJanMayen -> SvalbardAndJanMayen
                KeySweden -> Sweden
                KeySwitzerland -> Switzerland
                KeySyria -> Syria
                KeyTaiwan -> Taiwan
                KeyTajikistan -> Tajikistan
                KeyTanzania -> Tanzania
                KeyThailand -> Thailand
                KeyTimorLeste -> TimorLeste
                KeyTogo -> Togo
                KeyTokelau -> Tokelau
                KeyTonga -> Tonga
                KeyTrinidadAndTobago -> TrinidadAndTobago
                KeyTunisia -> Tunisia
                KeyTurkey -> Turkey
                KeyTurkmenistan -> Turkmenistan
                KeyTurksAndCaicosIslands -> TurksAndCaicosIslands
                KeyTuvalu -> Tuvalu
                KeyUganda -> Uganda
                KeyUkraine -> Ukraine
                KeyUnitedArabEmirates -> UnitedArabEmirates
                KeyUnitedKingdom -> UnitedKingdom
                KeyUnitedStates -> UnitedStates
                KeyUnitedStatesMinorOutlyingIslands -> UnitedStatesMinorOutlyingIslands
                KeyUruguay -> Uruguay
                KeyUzbekistan -> Uzbekistan
                KeyVanuatu -> Vanuatu
                KeyVenezuela -> Venezuela
                KeyVietnam -> Vietnam
                KeyVirginIslandsGB -> VirginIslandsGB
                KeyVirginIslandsUS -> VirginIslandsUS
                KeyWallisAndFutuna -> WallisAndFutuna
                KeyWesternSahara -> WesternSahara
                KeyYemen -> Yemen
                KeyZambia -> Zambia
                KeyZimbabwe -> Zimbabwe
                else -> Other(string)
            }
        }
    }
}