package com.algolia.search.model.places

import com.algolia.search.model.Raw
import com.algolia.search.model.places.Country.Other
import com.algolia.search.serialize.KeyAfghanistan
import com.algolia.search.serialize.KeyAlandIslands
import com.algolia.search.serialize.KeyAlbania
import com.algolia.search.serialize.KeyAlgeria
import com.algolia.search.serialize.KeyAmericanSamoa
import com.algolia.search.serialize.KeyAndorra
import com.algolia.search.serialize.KeyAngola
import com.algolia.search.serialize.KeyAnguilla
import com.algolia.search.serialize.KeyAntarctica
import com.algolia.search.serialize.KeyAntiguaAndBarbuda
import com.algolia.search.serialize.KeyArgentina
import com.algolia.search.serialize.KeyArmenia
import com.algolia.search.serialize.KeyAruba
import com.algolia.search.serialize.KeyAustralia
import com.algolia.search.serialize.KeyAustria
import com.algolia.search.serialize.KeyAzerbaijan
import com.algolia.search.serialize.KeyBahamas
import com.algolia.search.serialize.KeyBahrain
import com.algolia.search.serialize.KeyBailiwickOfGuernsey
import com.algolia.search.serialize.KeyBangladesh
import com.algolia.search.serialize.KeyBarbados
import com.algolia.search.serialize.KeyBelarus
import com.algolia.search.serialize.KeyBelgium
import com.algolia.search.serialize.KeyBelize
import com.algolia.search.serialize.KeyBenin
import com.algolia.search.serialize.KeyBermuda
import com.algolia.search.serialize.KeyBhutan
import com.algolia.search.serialize.KeyBolivia
import com.algolia.search.serialize.KeyBosniaAndHerzegovina
import com.algolia.search.serialize.KeyBotswana
import com.algolia.search.serialize.KeyBouvetIsland
import com.algolia.search.serialize.KeyBrazil
import com.algolia.search.serialize.KeyBritishIndianOceanTerritory
import com.algolia.search.serialize.KeyBruneiDarussalam
import com.algolia.search.serialize.KeyBulgaria
import com.algolia.search.serialize.KeyBurkinaFaso
import com.algolia.search.serialize.KeyBurundi
import com.algolia.search.serialize.KeyCaboVerde
import com.algolia.search.serialize.KeyCambodia
import com.algolia.search.serialize.KeyCameroon
import com.algolia.search.serialize.KeyCanada
import com.algolia.search.serialize.KeyCaribbeanNetherlands
import com.algolia.search.serialize.KeyCaymanIslands
import com.algolia.search.serialize.KeyCentralAfricanRepublic
import com.algolia.search.serialize.KeyChad
import com.algolia.search.serialize.KeyChile
import com.algolia.search.serialize.KeyChina
import com.algolia.search.serialize.KeyChristmasIsland
import com.algolia.search.serialize.KeyCocosIslands
import com.algolia.search.serialize.KeyColombia
import com.algolia.search.serialize.KeyComoros
import com.algolia.search.serialize.KeyCookIslands
import com.algolia.search.serialize.KeyCostaRica
import com.algolia.search.serialize.KeyCroatia
import com.algolia.search.serialize.KeyCuba
import com.algolia.search.serialize.KeyCuracao
import com.algolia.search.serialize.KeyCyprus
import com.algolia.search.serialize.KeyCzechRepublic
import com.algolia.search.serialize.KeyDemocraticRepublicOfTheCongo
import com.algolia.search.serialize.KeyDenmark
import com.algolia.search.serialize.KeyDjibouti
import com.algolia.search.serialize.KeyDominica
import com.algolia.search.serialize.KeyDominicanRepublic
import com.algolia.search.serialize.KeyEcuador
import com.algolia.search.serialize.KeyEgypt
import com.algolia.search.serialize.KeyElSalvador
import com.algolia.search.serialize.KeyEquatorialGuinea
import com.algolia.search.serialize.KeyEritrea
import com.algolia.search.serialize.KeyEstonia
import com.algolia.search.serialize.KeyEswatini
import com.algolia.search.serialize.KeyEthiopia
import com.algolia.search.serialize.KeyFalklandIslands
import com.algolia.search.serialize.KeyFaroeIslands
import com.algolia.search.serialize.KeyFiji
import com.algolia.search.serialize.KeyFinland
import com.algolia.search.serialize.KeyFrance
import com.algolia.search.serialize.KeyFrenchGuiana
import com.algolia.search.serialize.KeyFrenchPolynesia
import com.algolia.search.serialize.KeyFrenchSouthernAndAntarcticLands
import com.algolia.search.serialize.KeyGabon
import com.algolia.search.serialize.KeyGambia
import com.algolia.search.serialize.KeyGeorgia
import com.algolia.search.serialize.KeyGermany
import com.algolia.search.serialize.KeyGhana
import com.algolia.search.serialize.KeyGibraltar
import com.algolia.search.serialize.KeyGreece
import com.algolia.search.serialize.KeyGreenland
import com.algolia.search.serialize.KeyGrenada
import com.algolia.search.serialize.KeyGuadeloupe
import com.algolia.search.serialize.KeyGuam
import com.algolia.search.serialize.KeyGuatemala
import com.algolia.search.serialize.KeyGuinea
import com.algolia.search.serialize.KeyGuineaBissau
import com.algolia.search.serialize.KeyGuyana
import com.algolia.search.serialize.KeyHaiti
import com.algolia.search.serialize.KeyHeardIslandAndMcDonaldIslands
import com.algolia.search.serialize.KeyHonduras
import com.algolia.search.serialize.KeyHongKong
import com.algolia.search.serialize.KeyHungary
import com.algolia.search.serialize.KeyIceland
import com.algolia.search.serialize.KeyIndia
import com.algolia.search.serialize.KeyIndonesia
import com.algolia.search.serialize.KeyIran
import com.algolia.search.serialize.KeyIraq
import com.algolia.search.serialize.KeyIreland
import com.algolia.search.serialize.KeyIsleOfMan
import com.algolia.search.serialize.KeyIsrael
import com.algolia.search.serialize.KeyItaly
import com.algolia.search.serialize.KeyIvoryCoast
import com.algolia.search.serialize.KeyJamaica
import com.algolia.search.serialize.KeyJapan
import com.algolia.search.serialize.KeyJersey
import com.algolia.search.serialize.KeyJordan
import com.algolia.search.serialize.KeyKazakhstan
import com.algolia.search.serialize.KeyKenya
import com.algolia.search.serialize.KeyKiribati
import com.algolia.search.serialize.KeyKuwait
import com.algolia.search.serialize.KeyKyrgyzstan
import com.algolia.search.serialize.KeyLaos
import com.algolia.search.serialize.KeyLatvia
import com.algolia.search.serialize.KeyLebanon
import com.algolia.search.serialize.KeyLesotho
import com.algolia.search.serialize.KeyLiberia
import com.algolia.search.serialize.KeyLibya
import com.algolia.search.serialize.KeyLiechtenstein
import com.algolia.search.serialize.KeyLithuania
import com.algolia.search.serialize.KeyLuxembourg
import com.algolia.search.serialize.KeyMacau
import com.algolia.search.serialize.KeyMadagascar
import com.algolia.search.serialize.KeyMalawi
import com.algolia.search.serialize.KeyMalaysia
import com.algolia.search.serialize.KeyMaldives
import com.algolia.search.serialize.KeyMali
import com.algolia.search.serialize.KeyMalta
import com.algolia.search.serialize.KeyMarshallIslands
import com.algolia.search.serialize.KeyMartinique
import com.algolia.search.serialize.KeyMauritania
import com.algolia.search.serialize.KeyMauritius
import com.algolia.search.serialize.KeyMayotte
import com.algolia.search.serialize.KeyMexico
import com.algolia.search.serialize.KeyMicronesia
import com.algolia.search.serialize.KeyMoldova
import com.algolia.search.serialize.KeyMonaco
import com.algolia.search.serialize.KeyMongolia
import com.algolia.search.serialize.KeyMontenegro
import com.algolia.search.serialize.KeyMontserrat
import com.algolia.search.serialize.KeyMorocco
import com.algolia.search.serialize.KeyMozambique
import com.algolia.search.serialize.KeyMyanmar
import com.algolia.search.serialize.KeyNamibia
import com.algolia.search.serialize.KeyNauru
import com.algolia.search.serialize.KeyNepal
import com.algolia.search.serialize.KeyNetherlands
import com.algolia.search.serialize.KeyNewCaledonia
import com.algolia.search.serialize.KeyNewZealand
import com.algolia.search.serialize.KeyNicaragua
import com.algolia.search.serialize.KeyNiger
import com.algolia.search.serialize.KeyNigeria
import com.algolia.search.serialize.KeyNiue
import com.algolia.search.serialize.KeyNorfolkIsland
import com.algolia.search.serialize.KeyNorthKorea
import com.algolia.search.serialize.KeyNorthMacedonia
import com.algolia.search.serialize.KeyNorthernMarianaIslands
import com.algolia.search.serialize.KeyNorway
import com.algolia.search.serialize.KeyOman
import com.algolia.search.serialize.KeyPakistan
import com.algolia.search.serialize.KeyPalau
import com.algolia.search.serialize.KeyPalestine
import com.algolia.search.serialize.KeyPanama
import com.algolia.search.serialize.KeyPapuaNewGuinea
import com.algolia.search.serialize.KeyParaguay
import com.algolia.search.serialize.KeyPeru
import com.algolia.search.serialize.KeyPhilippines
import com.algolia.search.serialize.KeyPitcairnIslands
import com.algolia.search.serialize.KeyPoland
import com.algolia.search.serialize.KeyPortugal
import com.algolia.search.serialize.KeyPuertoRico
import com.algolia.search.serialize.KeyQatar
import com.algolia.search.serialize.KeyRepublicOfTheCongo
import com.algolia.search.serialize.KeyReunion
import com.algolia.search.serialize.KeyRomania
import com.algolia.search.serialize.KeyRussia
import com.algolia.search.serialize.KeyRwanda
import com.algolia.search.serialize.KeySaintBarthelemy
import com.algolia.search.serialize.KeySaintHelena
import com.algolia.search.serialize.KeySaintKittsAndNevis
import com.algolia.search.serialize.KeySaintLucia
import com.algolia.search.serialize.KeySaintMartin
import com.algolia.search.serialize.KeySaintPierreAndMiquelon
import com.algolia.search.serialize.KeySaintVincentAndTheGrenadines
import com.algolia.search.serialize.KeySamoa
import com.algolia.search.serialize.KeySanMarino
import com.algolia.search.serialize.KeySaoTomeAndPrincipe
import com.algolia.search.serialize.KeySaudiArabia
import com.algolia.search.serialize.KeySenegal
import com.algolia.search.serialize.KeySerbia
import com.algolia.search.serialize.KeySeychelles
import com.algolia.search.serialize.KeySierraLeone
import com.algolia.search.serialize.KeySingapore
import com.algolia.search.serialize.KeySintMaarten
import com.algolia.search.serialize.KeySlovakia
import com.algolia.search.serialize.KeySlovenia
import com.algolia.search.serialize.KeySolomonIslands
import com.algolia.search.serialize.KeySomalia
import com.algolia.search.serialize.KeySouthAfrica
import com.algolia.search.serialize.KeySouthGeorgiaAndTheSouthSandwichIslands
import com.algolia.search.serialize.KeySouthKorea
import com.algolia.search.serialize.KeySouthSudan
import com.algolia.search.serialize.KeySpain
import com.algolia.search.serialize.KeySriLanka
import com.algolia.search.serialize.KeySudan
import com.algolia.search.serialize.KeySuriname
import com.algolia.search.serialize.KeySvalbardAndJanMayen
import com.algolia.search.serialize.KeySweden
import com.algolia.search.serialize.KeySwitzerland
import com.algolia.search.serialize.KeySyria
import com.algolia.search.serialize.KeyTaiwan
import com.algolia.search.serialize.KeyTajikistan
import com.algolia.search.serialize.KeyTanzania
import com.algolia.search.serialize.KeyThailand
import com.algolia.search.serialize.KeyTimorLeste
import com.algolia.search.serialize.KeyTogo
import com.algolia.search.serialize.KeyTokelau
import com.algolia.search.serialize.KeyTonga
import com.algolia.search.serialize.KeyTrinidadAndTobago
import com.algolia.search.serialize.KeyTunisia
import com.algolia.search.serialize.KeyTurkey
import com.algolia.search.serialize.KeyTurkmenistan
import com.algolia.search.serialize.KeyTurksAndCaicosIslands
import com.algolia.search.serialize.KeyTuvalu
import com.algolia.search.serialize.KeyUganda
import com.algolia.search.serialize.KeyUkraine
import com.algolia.search.serialize.KeyUnitedArabEmirates
import com.algolia.search.serialize.KeyUnitedKingdom
import com.algolia.search.serialize.KeyUnitedStates
import com.algolia.search.serialize.KeyUnitedStatesMinorOutlyingIslands
import com.algolia.search.serialize.KeyUruguay
import com.algolia.search.serialize.KeyUzbekistan
import com.algolia.search.serialize.KeyVanuatu
import com.algolia.search.serialize.KeyVaticanCity
import com.algolia.search.serialize.KeyVenezuela
import com.algolia.search.serialize.KeyVietnam
import com.algolia.search.serialize.KeyVirginIslandsGB
import com.algolia.search.serialize.KeyVirginIslandsUS
import com.algolia.search.serialize.KeyWallisAndFutuna
import com.algolia.search.serialize.KeyWesternSahara
import com.algolia.search.serialize.KeyYemen
import com.algolia.search.serialize.KeyZambia
import com.algolia.search.serialize.KeyZimbabwe
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

    object Afghanistan : Country(KeyAfghanistan)
    object AlandIslands : Country(KeyAlandIslands)
    object Albania : Country(KeyAlbania)
    object Algeria : Country(KeyAlgeria)
    object AmericanSamoa : Country(KeyAmericanSamoa)
    object Andorra : Country(KeyAndorra)
    object Angola : Country(KeyAngola)
    object Anguilla : Country(KeyAnguilla)
    object Antarctica : Country(KeyAntarctica)
    object AntiguaAndBarbuda : Country(KeyAntiguaAndBarbuda)
    object Argentina : Country(KeyArgentina)
    object Armenia : Country(KeyArmenia)
    object Aruba : Country(KeyAruba)
    object Australia : Country(KeyAustralia)
    object Austria : Country(KeyAustria)
    object Azerbaijan : Country(KeyAzerbaijan)
    object Bahamas : Country(KeyBahamas)
    object Bahrain : Country(KeyBahrain)
    object Bangladesh : Country(KeyBangladesh)
    object Barbados : Country(KeyBarbados)
    object Belarus : Country(KeyBelarus)
    object Belgium : Country(KeyBelgium)
    object Belize : Country(KeyBelize)
    object Benin : Country(KeyBenin)
    object Bermuda : Country(KeyBermuda)
    object Bhutan : Country(KeyBhutan)
    object Bolivia : Country(KeyBolivia)
    object CaribbeanNetherlands : Country(KeyCaribbeanNetherlands)
    object BosniaAndHerzegovina : Country(KeyBosniaAndHerzegovina)
    object Botswana : Country(KeyBotswana)
    object BouvetIsland : Country(KeyBouvetIsland)
    object Brazil : Country(KeyBrazil)
    object BritishIndianOceanTerritory : Country(KeyBritishIndianOceanTerritory)
    object BruneiDarussalam : Country(KeyBruneiDarussalam)
    object Bulgaria : Country(KeyBulgaria)
    object BurkinaFaso : Country(KeyBurkinaFaso)
    object Burundi : Country(KeyBurundi)
    object CaboVerde : Country(KeyCaboVerde)
    object Cambodia : Country(KeyCambodia)
    object Cameroon : Country(KeyCameroon)
    object Canada : Country(KeyCanada)
    object CaymanIslands : Country(KeyCaymanIslands)
    object CentralAfricanRepublic : Country(KeyCentralAfricanRepublic)
    object Chad : Country(KeyChad)
    object Chile : Country(KeyChile)
    object China : Country(KeyChina)
    object ChristmasIsland : Country(KeyChristmasIsland)
    object CocosIslands : Country(KeyCocosIslands)
    object Colombia : Country(KeyColombia)
    object Comoros : Country(KeyComoros)
    object RepublicOfTheCongo : Country(KeyRepublicOfTheCongo)
    object DemocraticRepublicOfTheCongo : Country(KeyDemocraticRepublicOfTheCongo)
    object CookIslands : Country(KeyCookIslands)
    object CostaRica : Country(KeyCostaRica)
    object IvoryCoast : Country(KeyIvoryCoast)
    object Croatia : Country(KeyCroatia)
    object Cuba : Country(KeyCuba)
    object Curacao : Country(KeyCuracao)
    object Cyprus : Country(KeyCyprus)
    object CzechRepublic : Country(KeyCzechRepublic)
    object Denmark : Country(KeyDenmark)
    object Djibouti : Country(KeyDjibouti)
    object Dominica : Country(KeyDominica)
    object DominicanRepublic : Country(KeyDominicanRepublic)
    object Ecuador : Country(KeyEcuador)
    object Egypt : Country(KeyEgypt)
    object ElSalvador : Country(KeyElSalvador)
    object EquatorialGuinea : Country(KeyEquatorialGuinea)
    object Eritrea : Country(KeyEritrea)
    object Estonia : Country(KeyEstonia)
    object Eswatini : Country(KeyEswatini)
    object Ethiopia : Country(KeyEthiopia)
    object FalklandIslands : Country(KeyFalklandIslands)
    object FaroeIslands : Country(KeyFaroeIslands)
    object Fiji : Country(KeyFiji)
    object Finland : Country(KeyFinland)
    object France : Country(KeyFrance)
    object FrenchGuiana : Country(KeyFrenchGuiana)
    object FrenchPolynesia : Country(KeyFrenchPolynesia)
    object FrenchSouthernAndAntarcticLands : Country(KeyFrenchSouthernAndAntarcticLands)
    object Gabon : Country(KeyGabon)
    object Gambia : Country(KeyGambia)
    object Georgia : Country(KeyGeorgia)
    object Germany : Country(KeyGermany)
    object Ghana : Country(KeyGhana)
    object Gibraltar : Country(KeyGibraltar)
    object Greece : Country(KeyGreece)
    object Greenland : Country(KeyGreenland)
    object Grenada : Country(KeyGrenada)
    object Guadeloupe : Country(KeyGuadeloupe)
    object Guam : Country(KeyGuam)
    object Guatemala : Country(KeyGuatemala)
    object BailiwickOfGuernsey : Country(KeyBailiwickOfGuernsey)
    object Guinea : Country(KeyGuinea)
    object GuineaBissau : Country(KeyGuineaBissau)
    object Guyana : Country(KeyGuyana)
    object Haiti : Country(KeyHaiti)
    object HeardIslandAndMcDonaldIslands : Country(KeyHeardIslandAndMcDonaldIslands)
    object VaticanCity : Country(KeyVaticanCity)
    object Honduras : Country(KeyHonduras)
    object HongKong : Country(KeyHongKong)
    object Hungary : Country(KeyHungary)
    object Iceland : Country(KeyIceland)
    object India : Country(KeyIndia)
    object Indonesia : Country(KeyIndonesia)
    object Iran : Country(KeyIran)
    object Iraq : Country(KeyIraq)
    object Ireland : Country(KeyIreland)
    object IsleOfMan : Country(KeyIsleOfMan)
    object Israel : Country(KeyIsrael)
    object Italy : Country(KeyItaly)
    object Jamaica : Country(KeyJamaica)
    object Japan : Country(KeyJapan)
    object Jersey : Country(KeyJersey)
    object Jordan : Country(KeyJordan)
    object Kazakhstan : Country(KeyKazakhstan)
    object Kenya : Country(KeyKenya)
    object Kiribati : Country(KeyKiribati)
    object NorthKorea : Country(KeyNorthKorea)
    object SouthKorea : Country(KeySouthKorea)
    object Kuwait : Country(KeyKuwait)
    object Kyrgyzstan : Country(KeyKyrgyzstan)
    object Laos : Country(KeyLaos)
    object Latvia : Country(KeyLatvia)
    object Lebanon : Country(KeyLebanon)
    object Lesotho : Country(KeyLesotho)
    object Liberia : Country(KeyLiberia)
    object Libya : Country(KeyLibya)
    object Liechtenstein : Country(KeyLiechtenstein)
    object Lithuania : Country(KeyLithuania)
    object Luxembourg : Country(KeyLuxembourg)
    object Macau : Country(KeyMacau)
    object Madagascar : Country(KeyMadagascar)
    object Malawi : Country(KeyMalawi)
    object Malaysia : Country(KeyMalaysia)
    object Maldives : Country(KeyMaldives)
    object Mali : Country(KeyMali)
    object Malta : Country(KeyMalta)
    object MarshallIslands : Country(KeyMarshallIslands)
    object Martinique : Country(KeyMartinique)
    object Mauritania : Country(KeyMauritania)
    object Mauritius : Country(KeyMauritius)
    object Mayotte : Country(KeyMayotte)
    object Mexico : Country(KeyMexico)
    object Micronesia : Country(KeyMicronesia)
    object Moldova : Country(KeyMoldova)
    object Monaco : Country(KeyMonaco)
    object Mongolia : Country(KeyMongolia)
    object Montenegro : Country(KeyMontenegro)
    object Montserrat : Country(KeyMontserrat)
    object Morocco : Country(KeyMorocco)
    object Mozambique : Country(KeyMozambique)
    object Myanmar : Country(KeyMyanmar)
    object Namibia : Country(KeyNamibia)
    object Nauru : Country(KeyNauru)
    object Nepal : Country(KeyNepal)
    object Netherlands : Country(KeyNetherlands)
    object NewCaledonia : Country(KeyNewCaledonia)
    object NewZealand : Country(KeyNewZealand)
    object Nicaragua : Country(KeyNicaragua)
    object Niger : Country(KeyNiger)
    object Nigeria : Country(KeyNigeria)
    object Niue : Country(KeyNiue)
    object NorfolkIsland : Country(KeyNorfolkIsland)
    object NorthMacedonia : Country(KeyNorthMacedonia)
    object NorthernMarianaIslands : Country(KeyNorthernMarianaIslands)
    object Norway : Country(KeyNorway)
    object Oman : Country(KeyOman)
    object Pakistan : Country(KeyPakistan)
    object Palau : Country(KeyPalau)
    object Palestine : Country(KeyPalestine)
    object Panama : Country(KeyPanama)
    object PapuaNewGuinea : Country(KeyPapuaNewGuinea)
    object Paraguay : Country(KeyParaguay)
    object Peru : Country(KeyPeru)
    object Philippines : Country(KeyPhilippines)
    object PitcairnIslands : Country(KeyPitcairnIslands)
    object Poland : Country(KeyPoland)
    object Portugal : Country(KeyPortugal)
    object PuertoRico : Country(KeyPuertoRico)
    object Qatar : Country(KeyQatar)
    object Reunion : Country(KeyReunion)
    object Romania : Country(KeyRomania)
    object Russia : Country(KeyRussia)
    object Rwanda : Country(KeyRwanda)
    object SaintBarthelemy : Country(KeySaintBarthelemy)
    object SaintHelena : Country(KeySaintHelena)
    object SaintKittsAndNevis : Country(KeySaintKittsAndNevis)
    object SaintLucia : Country(KeySaintLucia)
    object SaintMartin : Country(KeySaintMartin)
    object SaintPierreAndMiquelon : Country(KeySaintPierreAndMiquelon)
    object SaintVincentAndTheGrenadines : Country(KeySaintVincentAndTheGrenadines)
    object Samoa : Country(KeySamoa)
    object SanMarino : Country(KeySanMarino)
    object SaoTomeAndPrincipe : Country(KeySaoTomeAndPrincipe)
    object SaudiArabia : Country(KeySaudiArabia)
    object Senegal : Country(KeySenegal)
    object Serbia : Country(KeySerbia)
    object Seychelles : Country(KeySeychelles)
    object SierraLeone : Country(KeySierraLeone)
    object Singapore : Country(KeySingapore)
    object SintMaarten : Country(KeySintMaarten)
    object Slovakia : Country(KeySlovakia)
    object Slovenia : Country(KeySlovenia)
    object SolomonIslands : Country(KeySolomonIslands)
    object Somalia : Country(KeySomalia)
    object SouthAfrica : Country(KeySouthAfrica)
    object SouthGeorgiaAndTheSouthSandwichIslands : Country(KeySouthGeorgiaAndTheSouthSandwichIslands)
    object SouthSudan : Country(KeySouthSudan)
    object Spain : Country(KeySpain)
    object SriLanka : Country(KeySriLanka)
    object Sudan : Country(KeySudan)
    object Suriname : Country(KeySuriname)
    object SvalbardAndJanMayen : Country(KeySvalbardAndJanMayen)
    object Sweden : Country(KeySweden)
    object Switzerland : Country(KeySwitzerland)
    object Syria : Country(KeySyria)
    object Taiwan : Country(KeyTaiwan)
    object Tajikistan : Country(KeyTajikistan)
    object Tanzania : Country(KeyTanzania)
    object Thailand : Country(KeyThailand)
    object TimorLeste : Country(KeyTimorLeste)
    object Togo : Country(KeyTogo)
    object Tokelau : Country(KeyTokelau)
    object Tonga : Country(KeyTonga)
    object TrinidadAndTobago : Country(KeyTrinidadAndTobago)
    object Tunisia : Country(KeyTunisia)
    object Turkey : Country(KeyTurkey)
    object Turkmenistan : Country(KeyTurkmenistan)
    object TurksAndCaicosIslands : Country(KeyTurksAndCaicosIslands)
    object Tuvalu : Country(KeyTuvalu)
    object Uganda : Country(KeyUganda)
    object Ukraine : Country(KeyUkraine)
    object UnitedArabEmirates : Country(KeyUnitedArabEmirates)
    object UnitedKingdom : Country(KeyUnitedKingdom)
    object UnitedStates : Country(KeyUnitedStates)
    object UnitedStatesMinorOutlyingIslands : Country(KeyUnitedStatesMinorOutlyingIslands)
    object Uruguay : Country(KeyUruguay)
    object Uzbekistan : Country(KeyUzbekistan)
    object Vanuatu : Country(KeyVanuatu)
    object Venezuela : Country(KeyVenezuela)
    object Vietnam : Country(KeyVietnam)
    object VirginIslandsGB : Country(KeyVirginIslandsGB)
    object VirginIslandsUS : Country(KeyVirginIslandsUS)
    object WallisAndFutuna : Country(KeyWallisAndFutuna)
    object WesternSahara : Country(KeyWesternSahara)
    object Yemen : Country(KeyYemen)
    object Zambia : Country(KeyZambia)
    object Zimbabwe : Country(KeyZimbabwe)

    data class Other(override val raw: String) : Country(raw)

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
