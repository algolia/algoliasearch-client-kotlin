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
import kotlinx.serialization.builtins.serializer

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

        private val serializer = String.serializer()

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
