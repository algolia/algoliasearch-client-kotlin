package com.algolia.search.dsl.places

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.places.Country

/**
 * DSL for building a [List] of [Country].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLCountries(
    private val countries: MutableList<Country> = mutableListOf()
) {

    val Afghanistan = Country.Afghanistan
    val AlandIslands = Country.AlandIslands
    val Albania = Country.Albania
    val Algeria = Country.Algeria
    val AmericanSamoa = Country.AmericanSamoa
    val Andorra = Country.Andorra
    val Angola = Country.Angola
    val Anguilla = Country.Anguilla
    val Antarctica = Country.Antarctica
    val AntiguaAndBarbuda = Country.AntiguaAndBarbuda
    val Argentina = Country.Argentina
    val Armenia = Country.Armenia
    val Aruba = Country.Aruba
    val Australia = Country.Australia
    val Austria = Country.Austria
    val Azerbaijan = Country.Azerbaijan
    val Bahamas = Country.Bahamas
    val Bahrain = Country.Bahrain
    val Bangladesh = Country.Bangladesh
    val Barbados = Country.Barbados
    val Belarus = Country.Belarus
    val Belgium = Country.Belgium
    val Belize = Country.Belize
    val Benin = Country.Benin
    val Bermuda = Country.Bermuda
    val Bhutan = Country.Bhutan
    val Bolivia = Country.Bolivia
    val CaribbeanNetherlands = Country.CaribbeanNetherlands
    val BosniaAndHerzegovina = Country.BosniaAndHerzegovina
    val Botswana = Country.Botswana
    val BouvetIsland = Country.BouvetIsland
    val Brazil = Country.Brazil
    val BritishIndianOceanTerritory = Country.BritishIndianOceanTerritory
    val BruneiDarussalam = Country.BruneiDarussalam
    val Bulgaria = Country.Bulgaria
    val BurkinaFaso = Country.BurkinaFaso
    val Burundi = Country.Burundi
    val CaboVerde = Country.CaboVerde
    val Cambodia = Country.Cambodia
    val Cameroon = Country.Cameroon
    val Canada = Country.Canada
    val CaymanIslands = Country.CaymanIslands
    val CentralAfricanRepublic = Country.CentralAfricanRepublic
    val Chad = Country.Chad
    val Chile = Country.Chile
    val China = Country.China
    val ChristmasIsland = Country.ChristmasIsland
    val CocosIslands = Country.CocosIslands
    val Colombia = Country.Colombia
    val Comoros = Country.Comoros
    val RepublicOfTheCongo = Country.RepublicOfTheCongo
    val DemocraticRepublicOfTheCongo = Country.DemocraticRepublicOfTheCongo
    val CookIslands = Country.CookIslands
    val CostaRica = Country.CostaRica
    val IvoryCoast = Country.IvoryCoast
    val Croatia = Country.Croatia
    val Cuba = Country.Cuba
    val Curacao = Country.Curacao
    val Cyprus = Country.Cyprus
    val CzechRepublic = Country.CzechRepublic
    val Denmark = Country.Denmark
    val Djibouti = Country.Djibouti
    val Dominica = Country.Dominica
    val DominicanRepublic = Country.DominicanRepublic
    val Ecuador = Country.Ecuador
    val Egypt = Country.Egypt
    val ElSalvador = Country.ElSalvador
    val EquatorialGuinea = Country.EquatorialGuinea
    val Eritrea = Country.Eritrea
    val Estonia = Country.Estonia
    val Eswatini = Country.Eswatini
    val Ethiopia = Country.Ethiopia
    val FalklandIslands = Country.FalklandIslands
    val FaroeIslands = Country.FaroeIslands
    val Fiji = Country.Fiji
    val Finland = Country.Finland
    val France = Country.France
    val FrenchGuiana = Country.FrenchGuiana
    val FrenchPolynesia = Country.FrenchPolynesia
    val FrenchSouthernAndAntarcticLands = Country.FrenchSouthernAndAntarcticLands
    val Gabon = Country.Gabon
    val Gambia = Country.Gambia
    val Georgia = Country.Georgia
    val Germany = Country.Germany
    val Ghana = Country.Ghana
    val Gibraltar = Country.Gibraltar
    val Greece = Country.Greece
    val Greenland = Country.Greenland
    val Grenada = Country.Grenada
    val Guadeloupe = Country.Guadeloupe
    val Guam = Country.Guam
    val Guatemala = Country.Guatemala
    val BailiwickOfGuernsey = Country.BailiwickOfGuernsey
    val Guinea = Country.Guinea
    val GuineaBissau = Country.GuineaBissau
    val Guyana = Country.Guyana
    val Haiti = Country.Haiti
    val HeardIslandAndMcDonaldIslands = Country.HeardIslandAndMcDonaldIslands
    val VaticanCity = Country.VaticanCity
    val Honduras = Country.Honduras
    val HongKong = Country.HongKong
    val Hungary = Country.Hungary
    val Iceland = Country.Iceland
    val India = Country.India
    val Indonesia = Country.Indonesia
    val Iran = Country.Iran
    val Iraq = Country.Iraq
    val Ireland = Country.Ireland
    val IsleOfMan = Country.IsleOfMan
    val Israel = Country.Israel
    val Italy = Country.Italy
    val Jamaica = Country.Jamaica
    val Japan = Country.Japan
    val Jersey = Country.Jersey
    val Jordan = Country.Jordan
    val Kazakhstan = Country.Kazakhstan
    val Kenya = Country.Kenya
    val Kiribati = Country.Kiribati
    val NorthKorea = Country.NorthKorea
    val SouthKorea = Country.SouthKorea
    val Kuwait = Country.Kuwait
    val Kyrgyzstan = Country.Kyrgyzstan
    val Laos = Country.Laos
    val Latvia = Country.Latvia
    val Lebanon = Country.Lebanon
    val Lesotho = Country.Lesotho
    val Liberia = Country.Liberia
    val Libya = Country.Libya
    val Liechtenstein = Country.Liechtenstein
    val Lithuania = Country.Lithuania
    val Luxembourg = Country.Luxembourg
    val Macau = Country.Macau
    val Madagascar = Country.Madagascar
    val Malawi = Country.Malawi
    val Malaysia = Country.Malaysia
    val Maldives = Country.Maldives
    val Mali = Country.Mali
    val Malta = Country.Malta
    val MarshallIslands = Country.MarshallIslands
    val Martinique = Country.Martinique
    val Mauritania = Country.Mauritania
    val Mauritius = Country.Mauritius
    val Mayotte = Country.Mayotte
    val Mexico = Country.Mexico
    val Micronesia = Country.Micronesia
    val Moldova = Country.Moldova
    val Monaco = Country.Monaco
    val Mongolia = Country.Mongolia
    val Montenegro = Country.Montenegro
    val Montserrat = Country.Montserrat
    val Morocco = Country.Morocco
    val Mozambique = Country.Mozambique
    val Myanmar = Country.Myanmar
    val Namibia = Country.Namibia
    val Nauru = Country.Nauru
    val Nepal = Country.Nepal
    val Netherlands = Country.Netherlands
    val NewCaledonia = Country.NewCaledonia
    val NewZealand = Country.NewZealand
    val Nicaragua = Country.Nicaragua
    val Niger = Country.Niger
    val Nigeria = Country.Nigeria
    val Niue = Country.Niue
    val NorfolkIsland = Country.NorfolkIsland
    val NorthMacedonia = Country.NorthMacedonia
    val NorthernMarianaIslands = Country.NorthernMarianaIslands
    val Norway = Country.Norway
    val Oman = Country.Oman
    val Pakistan = Country.Pakistan
    val Palau = Country.Palau
    val Palestine = Country.Palestine
    val Panama = Country.Panama
    val PapuaNewGuinea = Country.PapuaNewGuinea
    val Paraguay = Country.Paraguay
    val Peru = Country.Peru
    val Philippines = Country.Philippines
    val PitcairnIslands = Country.PitcairnIslands
    val Poland = Country.Poland
    val Portugal = Country.Portugal
    val PuertoRico = Country.PuertoRico
    val Qatar = Country.Qatar
    val Reunion = Country.Reunion
    val Romania = Country.Romania
    val Russia = Country.Russia
    val Rwanda = Country.Rwanda
    val SaintBarthelemy = Country.SaintBarthelemy
    val SaintHelena = Country.SaintHelena
    val SaintKittsAndNevis = Country.SaintKittsAndNevis
    val SaintLucia = Country.SaintLucia
    val SaintMartin = Country.SaintMartin
    val SaintPierreAndMiquelon = Country.SaintPierreAndMiquelon
    val SaintVincentAndTheGrenadines = Country.SaintVincentAndTheGrenadines
    val Samoa = Country.Samoa
    val SanMarino = Country.SanMarino
    val SaoTomeAndPrincipe = Country.SaoTomeAndPrincipe
    val SaudiArabia = Country.SaudiArabia
    val Senegal = Country.Senegal
    val Serbia = Country.Serbia
    val Seychelles = Country.Seychelles
    val SierraLeone = Country.SierraLeone
    val Singapore = Country.Singapore
    val SintMaarten = Country.SintMaarten
    val Slovakia = Country.Slovakia
    val Slovenia = Country.Slovenia
    val SolomonIslands = Country.SolomonIslands
    val Somalia = Country.Somalia
    val SouthAfrica = Country.SouthAfrica
    val SouthGeorgiaAndTheSouthSandwichIslands = Country.SouthGeorgiaAndTheSouthSandwichIslands
    val SouthSudan = Country.SouthSudan
    val Spain = Country.Spain
    val SriLanka = Country.SriLanka
    val Sudan = Country.Sudan
    val Suriname = Country.Suriname
    val SvalbardAndJanMayen = Country.SvalbardAndJanMayen
    val Sweden = Country.Sweden
    val Switzerland = Country.Switzerland
    val Syria = Country.Syria
    val Taiwan = Country.Taiwan
    val Tajikistan = Country.Tajikistan
    val Tanzania = Country.Tanzania
    val Thailand = Country.Thailand
    val TimorLeste = Country.TimorLeste
    val Togo = Country.Togo
    val Tokelau = Country.Tokelau
    val Tonga = Country.Tonga
    val TrinidadAndTobago = Country.TrinidadAndTobago
    val Tunisia = Country.Tunisia
    val Turkey = Country.Turkey
    val Turkmenistan = Country.Turkmenistan
    val TurksAndCaicosIslands = Country.TurksAndCaicosIslands
    val Tuvalu = Country.Tuvalu
    val Uganda = Country.Uganda
    val Ukraine = Country.Ukraine
    val UnitedArabEmirates = Country.UnitedArabEmirates
    val UnitedKingdom = Country.UnitedKingdom
    val UnitedStates = Country.UnitedStates
    val UnitedStatesMinorOutlyingIslands = Country.UnitedStatesMinorOutlyingIslands
    val Uruguay = Country.Uruguay
    val Uzbekistan = Country.Uzbekistan
    val Vanuatu = Country.Vanuatu
    val Venezuela = Country.Venezuela
    val Vietnam = Country.Vietnam
    val VirginIslandsGB = Country.VirginIslandsGB
    val VirginIslandsUS = Country.VirginIslandsUS
    val WallisAndFutuna = Country.WallisAndFutuna
    val WesternSahara = Country.WesternSahara
    val Yemen = Country.Yemen
    val Zambia = Country.Zambia
    val Zimbabwe = Country.Zimbabwe

    /**
     * Add [this] to [countries].
     */
    operator fun Country.unaryPlus() {
        countries += this
    }

    companion object : DSL<DSLCountries, List<Country>> {

        override operator fun invoke(block: DSLCountries.() -> Unit): List<Country> {
            return DSLCountries().apply(block).countries
        }
    }
}
