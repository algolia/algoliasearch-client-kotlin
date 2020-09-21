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

    public val Afghanistan: Country.Afghanistan = Country.Afghanistan
    public val AlandIslands: Country.AlandIslands = Country.AlandIslands
    public val Albania: Country.Albania = Country.Albania
    public val Algeria: Country.Algeria = Country.Algeria
    public val AmericanSamoa: Country.AmericanSamoa = Country.AmericanSamoa
    public val Andorra: Country.Andorra = Country.Andorra
    public val Angola: Country.Angola = Country.Angola
    public val Anguilla: Country.Anguilla = Country.Anguilla
    public val Antarctica: Country.Antarctica = Country.Antarctica
    public val AntiguaAndBarbuda: Country.AntiguaAndBarbuda = Country.AntiguaAndBarbuda
    public val Argentina: Country.Argentina = Country.Argentina
    public val Armenia: Country.Armenia = Country.Armenia
    public val Aruba: Country.Aruba = Country.Aruba
    public val Australia: Country.Australia = Country.Australia
    public val Austria: Country.Austria = Country.Austria
    public val Azerbaijan: Country.Azerbaijan = Country.Azerbaijan
    public val Bahamas: Country.Bahamas = Country.Bahamas
    public val Bahrain: Country.Bahrain = Country.Bahrain
    public val Bangladesh: Country.Bangladesh = Country.Bangladesh
    public val Barbados: Country.Barbados = Country.Barbados
    public val Belarus: Country.Belarus = Country.Belarus
    public val Belgium: Country.Belgium = Country.Belgium
    public val Belize: Country.Belize = Country.Belize
    public val Benin: Country.Benin = Country.Benin
    public val Bermuda: Country.Bermuda = Country.Bermuda
    public val Bhutan: Country.Bhutan = Country.Bhutan
    public val Bolivia: Country.Bolivia = Country.Bolivia
    public val CaribbeanNetherlands: Country.CaribbeanNetherlands = Country.CaribbeanNetherlands
    public val BosniaAndHerzegovina: Country.BosniaAndHerzegovina = Country.BosniaAndHerzegovina
    public val Botswana: Country.Botswana = Country.Botswana
    public val BouvetIsland: Country.BouvetIsland = Country.BouvetIsland
    public val Brazil: Country.Brazil = Country.Brazil
    public val BritishIndianOceanTerritory: Country.BritishIndianOceanTerritory = Country.BritishIndianOceanTerritory
    public val BruneiDarussalam: Country.BruneiDarussalam = Country.BruneiDarussalam
    public val Bulgaria: Country.Bulgaria = Country.Bulgaria
    public val BurkinaFaso: Country.BurkinaFaso = Country.BurkinaFaso
    public val Burundi: Country.Burundi = Country.Burundi
    public val CaboVerde: Country.CaboVerde = Country.CaboVerde
    public val Cambodia: Country.Cambodia = Country.Cambodia
    public val Cameroon: Country.Cameroon = Country.Cameroon
    public val Canada: Country.Canada = Country.Canada
    public val CaymanIslands: Country.CaymanIslands = Country.CaymanIslands
    public val CentralAfricanRepublic: Country.CentralAfricanRepublic = Country.CentralAfricanRepublic
    public val Chad: Country.Chad = Country.Chad
    public val Chile: Country.Chile = Country.Chile
    public val China: Country.China = Country.China
    public val ChristmasIsland: Country.ChristmasIsland = Country.ChristmasIsland
    public val CocosIslands: Country.CocosIslands = Country.CocosIslands
    public val Colombia: Country.Colombia = Country.Colombia
    public val Comoros: Country.Comoros = Country.Comoros
    public val RepublicOfTheCongo: Country.RepublicOfTheCongo = Country.RepublicOfTheCongo
    public val DemocraticRepublicOfTheCongo: Country.DemocraticRepublicOfTheCongo = Country.DemocraticRepublicOfTheCongo
    public val CookIslands: Country.CookIslands = Country.CookIslands
    public val CostaRica: Country.CostaRica = Country.CostaRica
    public val IvoryCoast: Country.IvoryCoast = Country.IvoryCoast
    public val Croatia: Country.Croatia = Country.Croatia
    public val Cuba: Country.Cuba = Country.Cuba
    public val Curacao: Country.Curacao = Country.Curacao
    public val Cyprus: Country.Cyprus = Country.Cyprus
    public val CzechRepublic: Country.CzechRepublic = Country.CzechRepublic
    public val Denmark: Country.Denmark = Country.Denmark
    public val Djibouti: Country.Djibouti = Country.Djibouti
    public val Dominica: Country.Dominica = Country.Dominica
    public val DominicanRepublic: Country.DominicanRepublic = Country.DominicanRepublic
    public val Ecuador: Country.Ecuador = Country.Ecuador
    public val Egypt: Country.Egypt = Country.Egypt
    public val ElSalvador: Country.ElSalvador = Country.ElSalvador
    public val EquatorialGuinea: Country.EquatorialGuinea = Country.EquatorialGuinea
    public val Eritrea: Country.Eritrea = Country.Eritrea
    public val Estonia: Country.Estonia = Country.Estonia
    public val Eswatini: Country.Eswatini = Country.Eswatini
    public val Ethiopia: Country.Ethiopia = Country.Ethiopia
    public val FalklandIslands: Country.FalklandIslands = Country.FalklandIslands
    public val FaroeIslands: Country.FaroeIslands = Country.FaroeIslands
    public val Fiji: Country.Fiji = Country.Fiji
    public val Finland: Country.Finland = Country.Finland
    public val France: Country.France = Country.France
    public val FrenchGuiana: Country.FrenchGuiana = Country.FrenchGuiana
    public val FrenchPolynesia: Country.FrenchPolynesia = Country.FrenchPolynesia
    public val FrenchSouthernAndAntarcticLands: Country.FrenchSouthernAndAntarcticLands =
        Country.FrenchSouthernAndAntarcticLands
    public val Gabon: Country.Gabon = Country.Gabon
    public val Gambia: Country.Gambia = Country.Gambia
    public val Georgia: Country.Georgia = Country.Georgia
    public val Germany: Country.Germany = Country.Germany
    public val Ghana: Country.Ghana = Country.Ghana
    public val Gibraltar: Country.Gibraltar = Country.Gibraltar
    public val Greece: Country.Greece = Country.Greece
    public val Greenland: Country.Greenland = Country.Greenland
    public val Grenada: Country.Grenada = Country.Grenada
    public val Guadeloupe: Country.Guadeloupe = Country.Guadeloupe
    public val Guam: Country.Guam = Country.Guam
    public val Guatemala: Country.Guatemala = Country.Guatemala
    public val BailiwickOfGuernsey: Country.BailiwickOfGuernsey = Country.BailiwickOfGuernsey
    public val Guinea: Country.Guinea = Country.Guinea
    public val GuineaBissau: Country.GuineaBissau = Country.GuineaBissau
    public val Guyana: Country.Guyana = Country.Guyana
    public val Haiti: Country.Haiti = Country.Haiti
    public val HeardIslandAndMcDonaldIslands: Country.HeardIslandAndMcDonaldIslands =
        Country.HeardIslandAndMcDonaldIslands
    public val VaticanCity: Country.VaticanCity = Country.VaticanCity
    public val Honduras: Country.Honduras = Country.Honduras
    public val HongKong: Country.HongKong = Country.HongKong
    public val Hungary: Country.Hungary = Country.Hungary
    public val Iceland: Country.Iceland = Country.Iceland
    public val India: Country.India = Country.India
    public val Indonesia: Country.Indonesia = Country.Indonesia
    public val Iran: Country.Iran = Country.Iran
    public val Iraq: Country.Iraq = Country.Iraq
    public val Ireland: Country.Ireland = Country.Ireland
    public val IsleOfMan: Country.IsleOfMan = Country.IsleOfMan
    public val Israel: Country.Israel = Country.Israel
    public val Italy: Country.Italy = Country.Italy
    public val Jamaica: Country.Jamaica = Country.Jamaica
    public val Japan: Country.Japan = Country.Japan
    public val Jersey: Country.Jersey = Country.Jersey
    public val Jordan: Country.Jordan = Country.Jordan
    public val Kazakhstan: Country.Kazakhstan = Country.Kazakhstan
    public val Kenya: Country.Kenya = Country.Kenya
    public val Kiribati: Country.Kiribati = Country.Kiribati
    public val NorthKorea: Country.NorthKorea = Country.NorthKorea
    public val SouthKorea: Country.SouthKorea = Country.SouthKorea
    public val Kuwait: Country.Kuwait = Country.Kuwait
    public val Kyrgyzstan: Country.Kyrgyzstan = Country.Kyrgyzstan
    public val Laos: Country.Laos = Country.Laos
    public val Latvia: Country.Latvia = Country.Latvia
    public val Lebanon: Country.Lebanon = Country.Lebanon
    public val Lesotho: Country.Lesotho = Country.Lesotho
    public val Liberia: Country.Liberia = Country.Liberia
    public val Libya: Country.Libya = Country.Libya
    public val Liechtenstein: Country.Liechtenstein = Country.Liechtenstein
    public val Lithuania: Country.Lithuania = Country.Lithuania
    public val Luxembourg: Country.Luxembourg = Country.Luxembourg
    public val Macau: Country.Macau = Country.Macau
    public val Madagascar: Country.Madagascar = Country.Madagascar
    public val Malawi: Country.Malawi = Country.Malawi
    public val Malaysia: Country.Malaysia = Country.Malaysia
    public val Maldives: Country.Maldives = Country.Maldives
    public val Mali: Country.Mali = Country.Mali
    public val Malta: Country.Malta = Country.Malta
    public val MarshallIslands: Country.MarshallIslands = Country.MarshallIslands
    public val Martinique: Country.Martinique = Country.Martinique
    public val Mauritania: Country.Mauritania = Country.Mauritania
    public val Mauritius: Country.Mauritius = Country.Mauritius
    public val Mayotte: Country.Mayotte = Country.Mayotte
    public val Mexico: Country.Mexico = Country.Mexico
    public val Micronesia: Country.Micronesia = Country.Micronesia
    public val Moldova: Country.Moldova = Country.Moldova
    public val Monaco: Country.Monaco = Country.Monaco
    public val Mongolia: Country.Mongolia = Country.Mongolia
    public val Montenegro: Country.Montenegro = Country.Montenegro
    public val Montserrat: Country.Montserrat = Country.Montserrat
    public val Morocco: Country.Morocco = Country.Morocco
    public val Mozambique: Country.Mozambique = Country.Mozambique
    public val Myanmar: Country.Myanmar = Country.Myanmar
    public val Namibia: Country.Namibia = Country.Namibia
    public val Nauru: Country.Nauru = Country.Nauru
    public val Nepal: Country.Nepal = Country.Nepal
    public val Netherlands: Country.Netherlands = Country.Netherlands
    public val NewCaledonia: Country.NewCaledonia = Country.NewCaledonia
    public val NewZealand: Country.NewZealand = Country.NewZealand
    public val Nicaragua: Country.Nicaragua = Country.Nicaragua
    public val Niger: Country.Niger = Country.Niger
    public val Nigeria: Country.Nigeria = Country.Nigeria
    public val Niue: Country.Niue = Country.Niue
    public val NorfolkIsland: Country.NorfolkIsland = Country.NorfolkIsland
    public val NorthMacedonia: Country.NorthMacedonia = Country.NorthMacedonia
    public val NorthernMarianaIslands: Country.NorthernMarianaIslands = Country.NorthernMarianaIslands
    public val Norway: Country.Norway = Country.Norway
    public val Oman: Country.Oman = Country.Oman
    public val Pakistan: Country.Pakistan = Country.Pakistan
    public val Palau: Country.Palau = Country.Palau
    public val Palestine: Country.Palestine = Country.Palestine
    public val Panama: Country.Panama = Country.Panama
    public val PapuaNewGuinea: Country.PapuaNewGuinea = Country.PapuaNewGuinea
    public val Paraguay: Country.Paraguay = Country.Paraguay
    public val Peru: Country.Peru = Country.Peru
    public val Philippines: Country.Philippines = Country.Philippines
    public val PitcairnIslands: Country.PitcairnIslands = Country.PitcairnIslands
    public val Poland: Country.Poland = Country.Poland
    public val Portugal: Country.Portugal = Country.Portugal
    public val PuertoRico: Country.PuertoRico = Country.PuertoRico
    public val Qatar: Country.Qatar = Country.Qatar
    public val Reunion: Country.Reunion = Country.Reunion
    public val Romania: Country.Romania = Country.Romania
    public val Russia: Country.Russia = Country.Russia
    public val Rwanda: Country.Rwanda = Country.Rwanda
    public val SaintBarthelemy: Country.SaintBarthelemy = Country.SaintBarthelemy
    public val SaintHelena: Country.SaintHelena = Country.SaintHelena
    public val SaintKittsAndNevis: Country.SaintKittsAndNevis = Country.SaintKittsAndNevis
    public val SaintLucia: Country.SaintLucia = Country.SaintLucia
    public val SaintMartin: Country.SaintMartin = Country.SaintMartin
    public val SaintPierreAndMiquelon: Country.SaintPierreAndMiquelon = Country.SaintPierreAndMiquelon
    public val SaintVincentAndTheGrenadines: Country.SaintVincentAndTheGrenadines = Country.SaintVincentAndTheGrenadines
    public val Samoa: Country.Samoa = Country.Samoa
    public val SanMarino: Country.SanMarino = Country.SanMarino
    public val SaoTomeAndPrincipe: Country.SaoTomeAndPrincipe = Country.SaoTomeAndPrincipe
    public val SaudiArabia: Country.SaudiArabia = Country.SaudiArabia
    public val Senegal: Country.Senegal = Country.Senegal
    public val Serbia: Country.Serbia = Country.Serbia
    public val Seychelles: Country.Seychelles = Country.Seychelles
    public val SierraLeone: Country.SierraLeone = Country.SierraLeone
    public val Singapore: Country.Singapore = Country.Singapore
    public val SintMaarten: Country.SintMaarten = Country.SintMaarten
    public val Slovakia: Country.Slovakia = Country.Slovakia
    public val Slovenia: Country.Slovenia = Country.Slovenia
    public val SolomonIslands: Country.SolomonIslands = Country.SolomonIslands
    public val Somalia: Country.Somalia = Country.Somalia
    public val SouthAfrica: Country.SouthAfrica = Country.SouthAfrica
    public val SouthGeorgiaAndTheSouthSandwichIslands: Country.SouthGeorgiaAndTheSouthSandwichIslands =
        Country.SouthGeorgiaAndTheSouthSandwichIslands
    public val SouthSudan: Country.SouthSudan = Country.SouthSudan
    public val Spain: Country.Spain = Country.Spain
    public val SriLanka: Country.SriLanka = Country.SriLanka
    public val Sudan: Country.Sudan = Country.Sudan
    public val Suriname: Country.Suriname = Country.Suriname
    public val SvalbardAndJanMayen: Country.SvalbardAndJanMayen = Country.SvalbardAndJanMayen
    public val Sweden: Country.Sweden = Country.Sweden
    public val Switzerland: Country.Switzerland = Country.Switzerland
    public val Syria: Country.Syria = Country.Syria
    public val Taiwan: Country.Taiwan = Country.Taiwan
    public val Tajikistan: Country.Tajikistan = Country.Tajikistan
    public val Tanzania: Country.Tanzania = Country.Tanzania
    public val Thailand: Country.Thailand = Country.Thailand
    public val TimorLeste: Country.TimorLeste = Country.TimorLeste
    public val Togo: Country.Togo = Country.Togo
    public val Tokelau: Country.Tokelau = Country.Tokelau
    public val Tonga: Country.Tonga = Country.Tonga
    public val TrinidadAndTobago: Country.TrinidadAndTobago = Country.TrinidadAndTobago
    public val Tunisia: Country.Tunisia = Country.Tunisia
    public val Turkey: Country.Turkey = Country.Turkey
    public val Turkmenistan: Country.Turkmenistan = Country.Turkmenistan
    public val TurksAndCaicosIslands: Country.TurksAndCaicosIslands = Country.TurksAndCaicosIslands
    public val Tuvalu: Country.Tuvalu = Country.Tuvalu
    public val Uganda: Country.Uganda = Country.Uganda
    public val Ukraine: Country.Ukraine = Country.Ukraine
    public val UnitedArabEmirates: Country.UnitedArabEmirates = Country.UnitedArabEmirates
    public val UnitedKingdom: Country.UnitedKingdom = Country.UnitedKingdom
    public val UnitedStates: Country.UnitedStates = Country.UnitedStates
    public val UnitedStatesMinorOutlyingIslands: Country.UnitedStatesMinorOutlyingIslands =
        Country.UnitedStatesMinorOutlyingIslands
    public val Uruguay: Country.Uruguay = Country.Uruguay
    public val Uzbekistan: Country.Uzbekistan = Country.Uzbekistan
    public val Vanuatu: Country.Vanuatu = Country.Vanuatu
    public val Venezuela: Country.Venezuela = Country.Venezuela
    public val Vietnam: Country.Vietnam = Country.Vietnam
    public val VirginIslandsGB: Country.VirginIslandsGB = Country.VirginIslandsGB
    public val VirginIslandsUS: Country.VirginIslandsUS = Country.VirginIslandsUS
    public val WallisAndFutuna: Country.WallisAndFutuna = Country.WallisAndFutuna
    public val WesternSahara: Country.WesternSahara = Country.WesternSahara
    public val Yemen: Country.Yemen = Country.Yemen
    public val Zambia: Country.Zambia = Country.Zambia
    public val Zimbabwe: Country.Zimbabwe = Country.Zimbabwe

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
