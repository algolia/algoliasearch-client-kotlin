package com.algolia.search.dsl.places

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.places.Country

/**
 * DSL for building a [List] of [Country].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLCountries(
    private val countries: MutableList<Country> = mutableListOf()
) {

    public val Afghanistan = Country.Afghanistan
    public val AlandIslands = Country.AlandIslands
    public val Albania = Country.Albania
    public val Algeria = Country.Algeria
    public val AmericanSamoa = Country.AmericanSamoa
    public val Andorra = Country.Andorra
    public val Angola = Country.Angola
    public val Anguilla = Country.Anguilla
    public val Antarctica = Country.Antarctica
    public val AntiguaAndBarbuda = Country.AntiguaAndBarbuda
    public val Argentina = Country.Argentina
    public val Armenia = Country.Armenia
    public val Aruba = Country.Aruba
    public val Australia = Country.Australia
    public val Austria = Country.Austria
    public val Azerbaijan = Country.Azerbaijan
    public val Bahamas = Country.Bahamas
    public val Bahrain = Country.Bahrain
    public val Bangladesh = Country.Bangladesh
    public val Barbados = Country.Barbados
    public val Belarus = Country.Belarus
    public val Belgium = Country.Belgium
    public val Belize = Country.Belize
    public val Benin = Country.Benin
    public val Bermuda = Country.Bermuda
    public val Bhutan = Country.Bhutan
    public val Bolivia = Country.Bolivia
    public val CaribbeanNetherlands = Country.CaribbeanNetherlands
    public val BosniaAndHerzegovina = Country.BosniaAndHerzegovina
    public val Botswana = Country.Botswana
    public val BouvetIsland = Country.BouvetIsland
    public val Brazil = Country.Brazil
    public val BritishIndianOceanTerritory = Country.BritishIndianOceanTerritory
    public val BruneiDarussalam = Country.BruneiDarussalam
    public val Bulgaria = Country.Bulgaria
    public val BurkinaFaso = Country.BurkinaFaso
    public val Burundi = Country.Burundi
    public val CaboVerde = Country.CaboVerde
    public val Cambodia = Country.Cambodia
    public val Cameroon = Country.Cameroon
    public val Canada = Country.Canada
    public val CaymanIslands = Country.CaymanIslands
    public val CentralAfricanRepublic = Country.CentralAfricanRepublic
    public val Chad = Country.Chad
    public val Chile = Country.Chile
    public val China = Country.China
    public val ChristmasIsland = Country.ChristmasIsland
    public val CocosIslands = Country.CocosIslands
    public val Colombia = Country.Colombia
    public val Comoros = Country.Comoros
    public val RepublicOfTheCongo = Country.RepublicOfTheCongo
    public val DemocraticRepublicOfTheCongo = Country.DemocraticRepublicOfTheCongo
    public val CookIslands = Country.CookIslands
    public val CostaRica = Country.CostaRica
    public val IvoryCoast = Country.IvoryCoast
    public val Croatia = Country.Croatia
    public val Cuba = Country.Cuba
    public val Curacao = Country.Curacao
    public val Cyprus = Country.Cyprus
    public val CzechRepublic = Country.CzechRepublic
    public val Denmark = Country.Denmark
    public val Djibouti = Country.Djibouti
    public val Dominica = Country.Dominica
    public val DominicanRepublic = Country.DominicanRepublic
    public val Ecuador = Country.Ecuador
    public val Egypt = Country.Egypt
    public val ElSalvador = Country.ElSalvador
    public val EquatorialGuinea = Country.EquatorialGuinea
    public val Eritrea = Country.Eritrea
    public val Estonia = Country.Estonia
    public val Eswatini = Country.Eswatini
    public val Ethiopia = Country.Ethiopia
    public val FalklandIslands = Country.FalklandIslands
    public val FaroeIslands = Country.FaroeIslands
    public val Fiji = Country.Fiji
    public val Finland = Country.Finland
    public val France = Country.France
    public val FrenchGuiana = Country.FrenchGuiana
    public val FrenchPolynesia = Country.FrenchPolynesia
    public val FrenchSouthernAndAntarcticLands = Country.FrenchSouthernAndAntarcticLands
    public val Gabon = Country.Gabon
    public val Gambia = Country.Gambia
    public val Georgia = Country.Georgia
    public val Germany = Country.Germany
    public val Ghana = Country.Ghana
    public val Gibraltar = Country.Gibraltar
    public val Greece = Country.Greece
    public val Greenland = Country.Greenland
    public val Grenada = Country.Grenada
    public val Guadeloupe = Country.Guadeloupe
    public val Guam = Country.Guam
    public val Guatemala = Country.Guatemala
    public val BailiwickOfGuernsey = Country.BailiwickOfGuernsey
    public val Guinea = Country.Guinea
    public val GuineaBissau = Country.GuineaBissau
    public val Guyana = Country.Guyana
    public val Haiti = Country.Haiti
    public val HeardIslandAndMcDonaldIslands = Country.HeardIslandAndMcDonaldIslands
    public val VaticanCity = Country.VaticanCity
    public val Honduras = Country.Honduras
    public val HongKong = Country.HongKong
    public val Hungary = Country.Hungary
    public val Iceland = Country.Iceland
    public val India = Country.India
    public val Indonesia = Country.Indonesia
    public val Iran = Country.Iran
    public val Iraq = Country.Iraq
    public val Ireland = Country.Ireland
    public val IsleOfMan = Country.IsleOfMan
    public val Israel = Country.Israel
    public val Italy = Country.Italy
    public val Jamaica = Country.Jamaica
    public val Japan = Country.Japan
    public val Jersey = Country.Jersey
    public val Jordan = Country.Jordan
    public val Kazakhstan = Country.Kazakhstan
    public val Kenya = Country.Kenya
    public val Kiribati = Country.Kiribati
    public val NorthKorea = Country.NorthKorea
    public val SouthKorea = Country.SouthKorea
    public val Kuwait = Country.Kuwait
    public val Kyrgyzstan = Country.Kyrgyzstan
    public val Laos = Country.Laos
    public val Latvia = Country.Latvia
    public val Lebanon = Country.Lebanon
    public val Lesotho = Country.Lesotho
    public val Liberia = Country.Liberia
    public val Libya = Country.Libya
    public val Liechtenstein = Country.Liechtenstein
    public val Lithuania = Country.Lithuania
    public val Luxembourg = Country.Luxembourg
    public val Macau = Country.Macau
    public val Madagascar = Country.Madagascar
    public val Malawi = Country.Malawi
    public val Malaysia = Country.Malaysia
    public val Maldives = Country.Maldives
    public val Mali = Country.Mali
    public val Malta = Country.Malta
    public val MarshallIslands = Country.MarshallIslands
    public val Martinique = Country.Martinique
    public val Mauritania = Country.Mauritania
    public val Mauritius = Country.Mauritius
    public val Mayotte = Country.Mayotte
    public val Mexico = Country.Mexico
    public val Micronesia = Country.Micronesia
    public val Moldova = Country.Moldova
    public val Monaco = Country.Monaco
    public val Mongolia = Country.Mongolia
    public val Montenegro = Country.Montenegro
    public val Montserrat = Country.Montserrat
    public val Morocco = Country.Morocco
    public val Mozambique = Country.Mozambique
    public val Myanmar = Country.Myanmar
    public val Namibia = Country.Namibia
    public val Nauru = Country.Nauru
    public val Nepal = Country.Nepal
    public val Netherlands = Country.Netherlands
    public val NewCaledonia = Country.NewCaledonia
    public val NewZealand = Country.NewZealand
    public val Nicaragua = Country.Nicaragua
    public val Niger = Country.Niger
    public val Nigeria = Country.Nigeria
    public val Niue = Country.Niue
    public val NorfolkIsland = Country.NorfolkIsland
    public val NorthMacedonia = Country.NorthMacedonia
    public val NorthernMarianaIslands = Country.NorthernMarianaIslands
    public val Norway = Country.Norway
    public val Oman = Country.Oman
    public val Pakistan = Country.Pakistan
    public val Palau = Country.Palau
    public val Palestine = Country.Palestine
    public val Panama = Country.Panama
    public val PapuaNewGuinea = Country.PapuaNewGuinea
    public val Paraguay = Country.Paraguay
    public val Peru = Country.Peru
    public val Philippines = Country.Philippines
    public val PitcairnIslands = Country.PitcairnIslands
    public val Poland = Country.Poland
    public val Portugal = Country.Portugal
    public val PuertoRico = Country.PuertoRico
    public val Qatar = Country.Qatar
    public val Reunion = Country.Reunion
    public val Romania = Country.Romania
    public val Russia = Country.Russia
    public val Rwanda = Country.Rwanda
    public val SaintBarthelemy = Country.SaintBarthelemy
    public val SaintHelena = Country.SaintHelena
    public val SaintKittsAndNevis = Country.SaintKittsAndNevis
    public val SaintLucia = Country.SaintLucia
    public val SaintMartin = Country.SaintMartin
    public val SaintPierreAndMiquelon = Country.SaintPierreAndMiquelon
    public val SaintVincentAndTheGrenadines = Country.SaintVincentAndTheGrenadines
    public val Samoa = Country.Samoa
    public val SanMarino = Country.SanMarino
    public val SaoTomeAndPrincipe = Country.SaoTomeAndPrincipe
    public val SaudiArabia = Country.SaudiArabia
    public val Senegal = Country.Senegal
    public val Serbia = Country.Serbia
    public val Seychelles = Country.Seychelles
    public val SierraLeone = Country.SierraLeone
    public val Singapore = Country.Singapore
    public val SintMaarten = Country.SintMaarten
    public val Slovakia = Country.Slovakia
    public val Slovenia = Country.Slovenia
    public val SolomonIslands = Country.SolomonIslands
    public val Somalia = Country.Somalia
    public val SouthAfrica = Country.SouthAfrica
    public val SouthGeorgiaAndTheSouthSandwichIslands = Country.SouthGeorgiaAndTheSouthSandwichIslands
    public val SouthSudan = Country.SouthSudan
    public val Spain = Country.Spain
    public val SriLanka = Country.SriLanka
    public val Sudan = Country.Sudan
    public val Suriname = Country.Suriname
    public val SvalbardAndJanMayen = Country.SvalbardAndJanMayen
    public val Sweden = Country.Sweden
    public val Switzerland = Country.Switzerland
    public val Syria = Country.Syria
    public val Taiwan = Country.Taiwan
    public val Tajikistan = Country.Tajikistan
    public val Tanzania = Country.Tanzania
    public val Thailand = Country.Thailand
    public val TimorLeste = Country.TimorLeste
    public val Togo = Country.Togo
    public val Tokelau = Country.Tokelau
    public val Tonga = Country.Tonga
    public val TrinidadAndTobago = Country.TrinidadAndTobago
    public val Tunisia = Country.Tunisia
    public val Turkey = Country.Turkey
    public val Turkmenistan = Country.Turkmenistan
    public val TurksAndCaicosIslands = Country.TurksAndCaicosIslands
    public val Tuvalu = Country.Tuvalu
    public val Uganda = Country.Uganda
    public val Ukraine = Country.Ukraine
    public val UnitedArabEmirates = Country.UnitedArabEmirates
    public val UnitedKingdom = Country.UnitedKingdom
    public val UnitedStates = Country.UnitedStates
    public val UnitedStatesMinorOutlyingIslands = Country.UnitedStatesMinorOutlyingIslands
    public val Uruguay = Country.Uruguay
    public val Uzbekistan = Country.Uzbekistan
    public val Vanuatu = Country.Vanuatu
    public val Venezuela = Country.Venezuela
    public val Vietnam = Country.Vietnam
    public val VirginIslandsGB = Country.VirginIslandsGB
    public val VirginIslandsUS = Country.VirginIslandsUS
    public val WallisAndFutuna = Country.WallisAndFutuna
    public val WesternSahara = Country.WesternSahara
    public val Yemen = Country.Yemen
    public val Zambia = Country.Zambia
    public val Zimbabwe = Country.Zimbabwe

    /**
     * Add [this] to [countries].
     */
    public operator fun Country.unaryPlus() {
        countries += this
    }

    public companion object : DSL<DSLCountries, List<Country>> {

        override operator fun invoke(block: DSLCountries.() -> Unit): List<Country> {
            return DSLCountries().apply(block).countries
        }
    }
}
