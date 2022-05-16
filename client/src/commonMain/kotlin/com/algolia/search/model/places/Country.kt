package com.algolia.search.model.places

import com.algolia.search.model.internal.Raw
import com.algolia.search.model.places.Country.Other
import com.algolia.search.serialize.internal.Countries
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * List of countries with associated country code.
 * You can pass two letters country codes (ISO 3166-1) using the [Other] class, but they need to be lower-cased.
 */
@Serializable(Country.Companion::class)
public sealed class Country(override val raw: String) : Raw<String> {

    public object Afghanistan : Country(Countries.Afghanistan)
    public object AlandIslands : Country(Countries.AlandIslands)
    public object Albania : Country(Countries.Albania)
    public object Algeria : Country(Countries.Algeria)
    public object AmericanSamoa : Country(Countries.AmericanSamoa)
    public object Andorra : Country(Countries.Andorra)
    public object Angola : Country(Countries.Angola)
    public object Anguilla : Country(Countries.Anguilla)
    public object Antarctica : Country(Countries.Antarctica)
    public object AntiguaAndBarbuda : Country(Countries.AntiguaAndBarbuda)
    public object Argentina : Country(Countries.Argentina)
    public object Armenia : Country(Countries.Armenia)
    public object Aruba : Country(Countries.Aruba)
    public object Australia : Country(Countries.Australia)
    public object Austria : Country(Countries.Austria)
    public object Azerbaijan : Country(Countries.Azerbaijan)
    public object Bahamas : Country(Countries.Bahamas)
    public object Bahrain : Country(Countries.Bahrain)
    public object Bangladesh : Country(Countries.Bangladesh)
    public object Barbados : Country(Countries.Barbados)
    public object Belarus : Country(Countries.Belarus)
    public object Belgium : Country(Countries.Belgium)
    public object Belize : Country(Countries.Belize)
    public object Benin : Country(Countries.Benin)
    public object Bermuda : Country(Countries.Bermuda)
    public object Bhutan : Country(Countries.Bhutan)
    public object Bolivia : Country(Countries.Bolivia)
    public object CaribbeanNetherlands : Country(Countries.CaribbeanNetherlands)
    public object BosniaAndHerzegovina : Country(Countries.BosniaAndHerzegovina)
    public object Botswana : Country(Countries.Botswana)
    public object BouvetIsland : Country(Countries.BouvetIsland)
    public object Brazil : Country(Countries.Brazil)
    public object BritishIndianOceanTerritory : Country(Countries.BritishIndianOceanTerritory)
    public object BruneiDarussalam : Country(Countries.BruneiDarussalam)
    public object Bulgaria : Country(Countries.Bulgaria)
    public object BurkinaFaso : Country(Countries.BurkinaFaso)
    public object Burundi : Country(Countries.Burundi)
    public object CaboVerde : Country(Countries.CaboVerde)
    public object Cambodia : Country(Countries.Cambodia)
    public object Cameroon : Country(Countries.Cameroon)
    public object Canada : Country(Countries.Canada)
    public object CaymanIslands : Country(Countries.CaymanIslands)
    public object CentralAfricanRepublic : Country(Countries.CentralAfricanRepublic)
    public object Chad : Country(Countries.Chad)
    public object Chile : Country(Countries.Chile)
    public object China : Country(Countries.China)
    public object ChristmasIsland : Country(Countries.ChristmasIsland)
    public object CocosIslands : Country(Countries.CocosIslands)
    public object Colombia : Country(Countries.Colombia)
    public object Comoros : Country(Countries.Comoros)
    public object RepublicOfTheCongo : Country(Countries.RepublicOfTheCongo)
    public object DemocraticRepublicOfTheCongo : Country(Countries.DemocraticRepublicOfTheCongo)
    public object CookIslands : Country(Countries.CookIslands)
    public object CostaRica : Country(Countries.CostaRica)
    public object IvoryCoast : Country(Countries.IvoryCoast)
    public object Croatia : Country(Countries.Croatia)
    public object Cuba : Country(Countries.Cuba)
    public object Curacao : Country(Countries.Curacao)
    public object Cyprus : Country(Countries.Cyprus)
    public object CzechRepublic : Country(Countries.CzechRepublic)
    public object Denmark : Country(Countries.Denmark)
    public object Djibouti : Country(Countries.Djibouti)
    public object Dominica : Country(Countries.Dominica)
    public object DominicanRepublic : Country(Countries.DominicanRepublic)
    public object Ecuador : Country(Countries.Ecuador)
    public object Egypt : Country(Countries.Egypt)
    public object ElSalvador : Country(Countries.ElSalvador)
    public object EquatorialGuinea : Country(Countries.EquatorialGuinea)
    public object Eritrea : Country(Countries.Eritrea)
    public object Estonia : Country(Countries.Estonia)
    public object Eswatini : Country(Countries.Eswatini)
    public object Ethiopia : Country(Countries.Ethiopia)
    public object FalklandIslands : Country(Countries.FalklandIslands)
    public object FaroeIslands : Country(Countries.FaroeIslands)
    public object Fiji : Country(Countries.Fiji)
    public object Finland : Country(Countries.Finland)
    public object France : Country(Countries.France)
    public object FrenchGuiana : Country(Countries.FrenchGuiana)
    public object FrenchPolynesia : Country(Countries.FrenchPolynesia)
    public object FrenchSouthernAndAntarcticLands : Country(Countries.FrenchSouthernAndAntarcticLands)
    public object Gabon : Country(Countries.Gabon)
    public object Gambia : Country(Countries.Gambia)
    public object Georgia : Country(Countries.Georgia)
    public object Germany : Country(Countries.Germany)
    public object Ghana : Country(Countries.Ghana)
    public object Gibraltar : Country(Countries.Gibraltar)
    public object Greece : Country(Countries.Greece)
    public object Greenland : Country(Countries.Greenland)
    public object Grenada : Country(Countries.Grenada)
    public object Guadeloupe : Country(Countries.Guadeloupe)
    public object Guam : Country(Countries.Guam)
    public object Guatemala : Country(Countries.Guatemala)
    public object BailiwickOfGuernsey : Country(Countries.BailiwickOfGuernsey)
    public object Guinea : Country(Countries.Guinea)
    public object GuineaBissau : Country(Countries.GuineaBissau)
    public object Guyana : Country(Countries.Guyana)
    public object Haiti : Country(Countries.Haiti)
    public object HeardIslandAndMcDonaldIslands : Country(Countries.HeardIslandAndMcDonaldIslands)
    public object VaticanCity : Country(Countries.VaticanCity)
    public object Honduras : Country(Countries.Honduras)
    public object HongKong : Country(Countries.HongKong)
    public object Hungary : Country(Countries.Hungary)
    public object Iceland : Country(Countries.Iceland)
    public object India : Country(Countries.India)
    public object Indonesia : Country(Countries.Indonesia)
    public object Iran : Country(Countries.Iran)
    public object Iraq : Country(Countries.Iraq)
    public object Ireland : Country(Countries.Ireland)
    public object IsleOfMan : Country(Countries.IsleOfMan)
    public object Israel : Country(Countries.Israel)
    public object Italy : Country(Countries.Italy)
    public object Jamaica : Country(Countries.Jamaica)
    public object Japan : Country(Countries.Japan)
    public object Jersey : Country(Countries.Jersey)
    public object Jordan : Country(Countries.Jordan)
    public object Kazakhstan : Country(Countries.Kazakhstan)
    public object Kenya : Country(Countries.Kenya)
    public object Kiribati : Country(Countries.Kiribati)
    public object NorthKorea : Country(Countries.NorthKorea)
    public object SouthKorea : Country(Countries.SouthKorea)
    public object Kuwait : Country(Countries.Kuwait)
    public object Kyrgyzstan : Country(Countries.Kyrgyzstan)
    public object Laos : Country(Countries.Laos)
    public object Latvia : Country(Countries.Latvia)
    public object Lebanon : Country(Countries.Lebanon)
    public object Lesotho : Country(Countries.Lesotho)
    public object Liberia : Country(Countries.Liberia)
    public object Libya : Country(Countries.Libya)
    public object Liechtenstein : Country(Countries.Liechtenstein)
    public object Lithuania : Country(Countries.Lithuania)
    public object Luxembourg : Country(Countries.Luxembourg)
    public object Macau : Country(Countries.Macau)
    public object Madagascar : Country(Countries.Madagascar)
    public object Malawi : Country(Countries.Malawi)
    public object Malaysia : Country(Countries.Malaysia)
    public object Maldives : Country(Countries.Maldives)
    public object Mali : Country(Countries.Mali)
    public object Malta : Country(Countries.Malta)
    public object MarshallIslands : Country(Countries.MarshallIslands)
    public object Martinique : Country(Countries.Martinique)
    public object Mauritania : Country(Countries.Mauritania)
    public object Mauritius : Country(Countries.Mauritius)
    public object Mayotte : Country(Countries.Mayotte)
    public object Mexico : Country(Countries.Mexico)
    public object Micronesia : Country(Countries.Micronesia)
    public object Moldova : Country(Countries.Moldova)
    public object Monaco : Country(Countries.Monaco)
    public object Mongolia : Country(Countries.Mongolia)
    public object Montenegro : Country(Countries.Montenegro)
    public object Montserrat : Country(Countries.Montserrat)
    public object Morocco : Country(Countries.Morocco)
    public object Mozambique : Country(Countries.Mozambique)
    public object Myanmar : Country(Countries.Myanmar)
    public object Namibia : Country(Countries.Namibia)
    public object Nauru : Country(Countries.Nauru)
    public object Nepal : Country(Countries.Nepal)
    public object Netherlands : Country(Countries.Netherlands)
    public object NewCaledonia : Country(Countries.NewCaledonia)
    public object NewZealand : Country(Countries.NewZealand)
    public object Nicaragua : Country(Countries.Nicaragua)
    public object Niger : Country(Countries.Niger)
    public object Nigeria : Country(Countries.Nigeria)
    public object Niue : Country(Countries.Niue)
    public object NorfolkIsland : Country(Countries.NorfolkIsland)
    public object NorthMacedonia : Country(Countries.NorthMacedonia)
    public object NorthernMarianaIslands : Country(Countries.NorthernMarianaIslands)
    public object Norway : Country(Countries.Norway)
    public object Oman : Country(Countries.Oman)
    public object Pakistan : Country(Countries.Pakistan)
    public object Palau : Country(Countries.Palau)
    public object Palestine : Country(Countries.Palestine)
    public object Panama : Country(Countries.Panama)
    public object PapuaNewGuinea : Country(Countries.PapuaNewGuinea)
    public object Paraguay : Country(Countries.Paraguay)
    public object Peru : Country(Countries.Peru)
    public object Philippines : Country(Countries.Philippines)
    public object PitcairnIslands : Country(Countries.PitcairnIslands)
    public object Poland : Country(Countries.Poland)
    public object Portugal : Country(Countries.Portugal)
    public object PuertoRico : Country(Countries.PuertoRico)
    public object Qatar : Country(Countries.Qatar)
    public object Reunion : Country(Countries.Reunion)
    public object Romania : Country(Countries.Romania)
    public object Russia : Country(Countries.Russia)
    public object Rwanda : Country(Countries.Rwanda)
    public object SaintBarthelemy : Country(Countries.SaintBarthelemy)
    public object SaintHelena : Country(Countries.SaintHelena)
    public object SaintKittsAndNevis : Country(Countries.SaintKittsAndNevis)
    public object SaintLucia : Country(Countries.SaintLucia)
    public object SaintMartin : Country(Countries.SaintMartin)
    public object SaintPierreAndMiquelon : Country(Countries.SaintPierreAndMiquelon)
    public object SaintVincentAndTheGrenadines : Country(Countries.SaintVincentAndTheGrenadines)
    public object Samoa : Country(Countries.Samoa)
    public object SanMarino : Country(Countries.SanMarino)
    public object SaoTomeAndPrincipe : Country(Countries.SaoTomeAndPrincipe)
    public object SaudiArabia : Country(Countries.SaudiArabia)
    public object Senegal : Country(Countries.Senegal)
    public object Serbia : Country(Countries.Serbia)
    public object Seychelles : Country(Countries.Seychelles)
    public object SierraLeone : Country(Countries.SierraLeone)
    public object Singapore : Country(Countries.Singapore)
    public object SintMaarten : Country(Countries.SintMaarten)
    public object Slovakia : Country(Countries.Slovakia)
    public object Slovenia : Country(Countries.Slovenia)
    public object SolomonIslands : Country(Countries.SolomonIslands)
    public object Somalia : Country(Countries.Somalia)
    public object SouthAfrica : Country(Countries.SouthAfrica)
    public object SouthGeorgiaAndTheSouthSandwichIslands : Country(Countries.SouthGeorgiaAndTheSouthSandwichIslands)
    public object SouthSudan : Country(Countries.SouthSudan)
    public object Spain : Country(Countries.Spain)
    public object SriLanka : Country(Countries.SriLanka)
    public object Sudan : Country(Countries.Sudan)
    public object Suriname : Country(Countries.Suriname)
    public object SvalbardAndJanMayen : Country(Countries.SvalbardAndJanMayen)
    public object Sweden : Country(Countries.Sweden)
    public object Switzerland : Country(Countries.Switzerland)
    public object Syria : Country(Countries.Syria)
    public object Taiwan : Country(Countries.Taiwan)
    public object Tajikistan : Country(Countries.Tajikistan)
    public object Tanzania : Country(Countries.Tanzania)
    public object Thailand : Country(Countries.Thailand)
    public object TimorLeste : Country(Countries.TimorLeste)
    public object Togo : Country(Countries.Togo)
    public object Tokelau : Country(Countries.Tokelau)
    public object Tonga : Country(Countries.Tonga)
    public object TrinidadAndTobago : Country(Countries.TrinidadAndTobago)
    public object Tunisia : Country(Countries.Tunisia)
    public object Turkey : Country(Countries.Turkey)
    public object Turkmenistan : Country(Countries.Turkmenistan)
    public object TurksAndCaicosIslands : Country(Countries.TurksAndCaicosIslands)
    public object Tuvalu : Country(Countries.Tuvalu)
    public object Uganda : Country(Countries.Uganda)
    public object Ukraine : Country(Countries.Ukraine)
    public object UnitedArabEmirates : Country(Countries.UnitedArabEmirates)
    public object UnitedKingdom : Country(Countries.UnitedKingdom)
    public object UnitedStates : Country(Countries.UnitedStates)
    public object UnitedStatesMinorOutlyingIslands : Country(Countries.UnitedStatesMinorOutlyingIslands)
    public object Uruguay : Country(Countries.Uruguay)
    public object Uzbekistan : Country(Countries.Uzbekistan)
    public object Vanuatu : Country(Countries.Vanuatu)
    public object Venezuela : Country(Countries.Venezuela)
    public object Vietnam : Country(Countries.Vietnam)
    public object VirginIslandsGB : Country(Countries.VirginIslandsGB)
    public object VirginIslandsUS : Country(Countries.VirginIslandsUS)
    public object WallisAndFutuna : Country(Countries.WallisAndFutuna)
    public object WesternSahara : Country(Countries.WesternSahara)
    public object Yemen : Country(Countries.Yemen)
    public object Zambia : Country(Countries.Zambia)
    public object Zimbabwe : Country(Countries.Zimbabwe)

    public data class Other(override val raw: String) : Country(raw)

    public companion object : KSerializer<Country> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Country) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Country {
            return when (val string = serializer.deserialize(decoder)) {
                Countries.Afghanistan -> Afghanistan
                Countries.AlandIslands -> AlandIslands
                Countries.Albania -> Albania
                Countries.Algeria -> Algeria
                Countries.AmericanSamoa -> AmericanSamoa
                Countries.Andorra -> Andorra
                Countries.Angola -> Angola
                Countries.Anguilla -> Anguilla
                Countries.Antarctica -> Antarctica
                Countries.AntiguaAndBarbuda -> AntiguaAndBarbuda
                Countries.Argentina -> Argentina
                Countries.Armenia -> Armenia
                Countries.Aruba -> Aruba
                Countries.Australia -> Australia
                Countries.Austria -> Austria
                Countries.Azerbaijan -> Azerbaijan
                Countries.Bahamas -> Bahamas
                Countries.Bahrain -> Bahrain
                Countries.Bangladesh -> Bangladesh
                Countries.Barbados -> Barbados
                Countries.Belarus -> Belarus
                Countries.Belgium -> Belgium
                Countries.Belize -> Belize
                Countries.Benin -> Benin
                Countries.Bermuda -> Bermuda
                Countries.Bhutan -> Bhutan
                Countries.Bolivia -> Bolivia
                Countries.CaribbeanNetherlands -> CaribbeanNetherlands
                Countries.BosniaAndHerzegovina -> BosniaAndHerzegovina
                Countries.Botswana -> Botswana
                Countries.BouvetIsland -> BouvetIsland
                Countries.Brazil -> Brazil
                Countries.BritishIndianOceanTerritory -> BritishIndianOceanTerritory
                Countries.BruneiDarussalam -> BruneiDarussalam
                Countries.Bulgaria -> Bulgaria
                Countries.BurkinaFaso -> BurkinaFaso
                Countries.Burundi -> Burundi
                Countries.CaboVerde -> CaboVerde
                Countries.Cambodia -> Cambodia
                Countries.Cameroon -> Cameroon
                Countries.Canada -> Canada
                Countries.CaymanIslands -> CaymanIslands
                Countries.CentralAfricanRepublic -> CentralAfricanRepublic
                Countries.Chad -> Chad
                Countries.Chile -> Chile
                Countries.China -> China
                Countries.ChristmasIsland -> ChristmasIsland
                Countries.CocosIslands -> CocosIslands
                Countries.Colombia -> Colombia
                Countries.Comoros -> Comoros
                Countries.RepublicOfTheCongo -> RepublicOfTheCongo
                Countries.DemocraticRepublicOfTheCongo -> DemocraticRepublicOfTheCongo
                Countries.CookIslands -> CookIslands
                Countries.CostaRica -> CostaRica
                Countries.IvoryCoast -> IvoryCoast
                Countries.Croatia -> Croatia
                Countries.Cuba -> Cuba
                Countries.Curacao -> Curacao
                Countries.Cyprus -> Cyprus
                Countries.CzechRepublic -> CzechRepublic
                Countries.Denmark -> Denmark
                Countries.Djibouti -> Djibouti
                Countries.Dominica -> Dominica
                Countries.DominicanRepublic -> DominicanRepublic
                Countries.Ecuador -> Ecuador
                Countries.Egypt -> Egypt
                Countries.ElSalvador -> ElSalvador
                Countries.EquatorialGuinea -> EquatorialGuinea
                Countries.Eritrea -> Eritrea
                Countries.Estonia -> Estonia
                Countries.Eswatini -> Eswatini
                Countries.Ethiopia -> Ethiopia
                Countries.FalklandIslands -> FalklandIslands
                Countries.FaroeIslands -> FaroeIslands
                Countries.Fiji -> Fiji
                Countries.Finland -> Finland
                Countries.France -> France
                Countries.FrenchGuiana -> FrenchGuiana
                Countries.FrenchPolynesia -> FrenchPolynesia
                Countries.FrenchSouthernAndAntarcticLands -> FrenchSouthernAndAntarcticLands
                Countries.Gabon -> Gabon
                Countries.Gambia -> Gambia
                Countries.Georgia -> Georgia
                Countries.Germany -> Germany
                Countries.Ghana -> Ghana
                Countries.Gibraltar -> Gibraltar
                Countries.Greece -> Greece
                Countries.Greenland -> Greenland
                Countries.Grenada -> Grenada
                Countries.Guadeloupe -> Guadeloupe
                Countries.Guam -> Guam
                Countries.Guatemala -> Guatemala
                Countries.BailiwickOfGuernsey -> BailiwickOfGuernsey
                Countries.Guinea -> Guinea
                Countries.GuineaBissau -> GuineaBissau
                Countries.Guyana -> Guyana
                Countries.Haiti -> Haiti
                Countries.HeardIslandAndMcDonaldIslands -> HeardIslandAndMcDonaldIslands
                Countries.VaticanCity -> VaticanCity
                Countries.Honduras -> Honduras
                Countries.HongKong -> HongKong
                Countries.Hungary -> Hungary
                Countries.Iceland -> Iceland
                Countries.India -> India
                Countries.Indonesia -> Indonesia
                Countries.Iran -> Iran
                Countries.Iraq -> Iraq
                Countries.Ireland -> Ireland
                Countries.IsleOfMan -> IsleOfMan
                Countries.Israel -> Israel
                Countries.Italy -> Italy
                Countries.Jamaica -> Jamaica
                Countries.Japan -> Japan
                Countries.Jersey -> Jersey
                Countries.Jordan -> Jordan
                Countries.Kazakhstan -> Kazakhstan
                Countries.Kenya -> Kenya
                Countries.Kiribati -> Kiribati
                Countries.NorthKorea -> NorthKorea
                Countries.SouthKorea -> SouthKorea
                Countries.Kuwait -> Kuwait
                Countries.Kyrgyzstan -> Kyrgyzstan
                Countries.Laos -> Laos
                Countries.Latvia -> Latvia
                Countries.Lebanon -> Lebanon
                Countries.Lesotho -> Lesotho
                Countries.Liberia -> Liberia
                Countries.Libya -> Libya
                Countries.Liechtenstein -> Liechtenstein
                Countries.Lithuania -> Lithuania
                Countries.Luxembourg -> Luxembourg
                Countries.Macau -> Macau
                Countries.Madagascar -> Madagascar
                Countries.Malawi -> Malawi
                Countries.Malaysia -> Malaysia
                Countries.Maldives -> Maldives
                Countries.Mali -> Mali
                Countries.Malta -> Malta
                Countries.MarshallIslands -> MarshallIslands
                Countries.Martinique -> Martinique
                Countries.Mauritania -> Mauritania
                Countries.Mauritius -> Mauritius
                Countries.Mayotte -> Mayotte
                Countries.Mexico -> Mexico
                Countries.Micronesia -> Micronesia
                Countries.Moldova -> Moldova
                Countries.Monaco -> Monaco
                Countries.Mongolia -> Mongolia
                Countries.Montenegro -> Montenegro
                Countries.Montserrat -> Montserrat
                Countries.Morocco -> Morocco
                Countries.Mozambique -> Mozambique
                Countries.Myanmar -> Myanmar
                Countries.Namibia -> Namibia
                Countries.Nauru -> Nauru
                Countries.Nepal -> Nepal
                Countries.Netherlands -> Netherlands
                Countries.NewCaledonia -> NewCaledonia
                Countries.NewZealand -> NewZealand
                Countries.Nicaragua -> Nicaragua
                Countries.Niger -> Niger
                Countries.Nigeria -> Nigeria
                Countries.Niue -> Niue
                Countries.NorfolkIsland -> NorfolkIsland
                Countries.NorthMacedonia -> NorthMacedonia
                Countries.NorthernMarianaIslands -> NorthernMarianaIslands
                Countries.Norway -> Norway
                Countries.Oman -> Oman
                Countries.Pakistan -> Pakistan
                Countries.Palau -> Palau
                Countries.Palestine -> Palestine
                Countries.Panama -> Panama
                Countries.PapuaNewGuinea -> PapuaNewGuinea
                Countries.Paraguay -> Paraguay
                Countries.Peru -> Peru
                Countries.Philippines -> Philippines
                Countries.PitcairnIslands -> PitcairnIslands
                Countries.Poland -> Poland
                Countries.Portugal -> Portugal
                Countries.PuertoRico -> PuertoRico
                Countries.Qatar -> Qatar
                Countries.Reunion -> Reunion
                Countries.Romania -> Romania
                Countries.Russia -> Russia
                Countries.Rwanda -> Rwanda
                Countries.SaintBarthelemy -> SaintBarthelemy
                Countries.SaintHelena -> SaintHelena
                Countries.SaintKittsAndNevis -> SaintKittsAndNevis
                Countries.SaintLucia -> SaintLucia
                Countries.SaintMartin -> SaintMartin
                Countries.SaintPierreAndMiquelon -> SaintPierreAndMiquelon
                Countries.SaintVincentAndTheGrenadines -> SaintVincentAndTheGrenadines
                Countries.Samoa -> Samoa
                Countries.SanMarino -> SanMarino
                Countries.SaoTomeAndPrincipe -> SaoTomeAndPrincipe
                Countries.SaudiArabia -> SaudiArabia
                Countries.Senegal -> Senegal
                Countries.Serbia -> Serbia
                Countries.Seychelles -> Seychelles
                Countries.SierraLeone -> SierraLeone
                Countries.Singapore -> Singapore
                Countries.SintMaarten -> SintMaarten
                Countries.Slovakia -> Slovakia
                Countries.Slovenia -> Slovenia
                Countries.SolomonIslands -> SolomonIslands
                Countries.Somalia -> Somalia
                Countries.SouthAfrica -> SouthAfrica
                Countries.SouthGeorgiaAndTheSouthSandwichIslands -> SouthGeorgiaAndTheSouthSandwichIslands
                Countries.SouthSudan -> SouthSudan
                Countries.Spain -> Spain
                Countries.SriLanka -> SriLanka
                Countries.Sudan -> Sudan
                Countries.Suriname -> Suriname
                Countries.SvalbardAndJanMayen -> SvalbardAndJanMayen
                Countries.Sweden -> Sweden
                Countries.Switzerland -> Switzerland
                Countries.Syria -> Syria
                Countries.Taiwan -> Taiwan
                Countries.Tajikistan -> Tajikistan
                Countries.Tanzania -> Tanzania
                Countries.Thailand -> Thailand
                Countries.TimorLeste -> TimorLeste
                Countries.Togo -> Togo
                Countries.Tokelau -> Tokelau
                Countries.Tonga -> Tonga
                Countries.TrinidadAndTobago -> TrinidadAndTobago
                Countries.Tunisia -> Tunisia
                Countries.Turkey -> Turkey
                Countries.Turkmenistan -> Turkmenistan
                Countries.TurksAndCaicosIslands -> TurksAndCaicosIslands
                Countries.Tuvalu -> Tuvalu
                Countries.Uganda -> Uganda
                Countries.Ukraine -> Ukraine
                Countries.UnitedArabEmirates -> UnitedArabEmirates
                Countries.UnitedKingdom -> UnitedKingdom
                Countries.UnitedStates -> UnitedStates
                Countries.UnitedStatesMinorOutlyingIslands -> UnitedStatesMinorOutlyingIslands
                Countries.Uruguay -> Uruguay
                Countries.Uzbekistan -> Uzbekistan
                Countries.Vanuatu -> Vanuatu
                Countries.Venezuela -> Venezuela
                Countries.Vietnam -> Vietnam
                Countries.VirginIslandsGB -> VirginIslandsGB
                Countries.VirginIslandsUS -> VirginIslandsUS
                Countries.WallisAndFutuna -> WallisAndFutuna
                Countries.WesternSahara -> WesternSahara
                Countries.Yemen -> Yemen
                Countries.Zambia -> Zambia
                Countries.Zimbabwe -> Zimbabwe
                else -> Other(string)
            }
        }
    }
}
