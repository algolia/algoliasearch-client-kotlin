package serialize.places

import com.algolia.search.model.places.Country
import com.algolia.search.model.places.Country.Afghanistan
import com.algolia.search.model.places.Country.AlandIslands
import com.algolia.search.model.places.Country.Albania
import com.algolia.search.model.places.Country.Algeria
import com.algolia.search.model.places.Country.AmericanSamoa
import com.algolia.search.model.places.Country.Andorra
import com.algolia.search.model.places.Country.Angola
import com.algolia.search.model.places.Country.Anguilla
import com.algolia.search.model.places.Country.Antarctica
import com.algolia.search.model.places.Country.AntiguaAndBarbuda
import com.algolia.search.model.places.Country.Argentina
import com.algolia.search.model.places.Country.Armenia
import com.algolia.search.model.places.Country.Aruba
import com.algolia.search.model.places.Country.Australia
import com.algolia.search.model.places.Country.Austria
import com.algolia.search.model.places.Country.Azerbaijan
import com.algolia.search.model.places.Country.Bahamas
import com.algolia.search.model.places.Country.Bahrain
import com.algolia.search.model.places.Country.BailiwickOfGuernsey
import com.algolia.search.model.places.Country.Bangladesh
import com.algolia.search.model.places.Country.Barbados
import com.algolia.search.model.places.Country.Belarus
import com.algolia.search.model.places.Country.Belgium
import com.algolia.search.model.places.Country.Belize
import com.algolia.search.model.places.Country.Benin
import com.algolia.search.model.places.Country.Bermuda
import com.algolia.search.model.places.Country.Bhutan
import com.algolia.search.model.places.Country.Bolivia
import com.algolia.search.model.places.Country.BosniaAndHerzegovina
import com.algolia.search.model.places.Country.Botswana
import com.algolia.search.model.places.Country.BouvetIsland
import com.algolia.search.model.places.Country.Brazil
import com.algolia.search.model.places.Country.BritishIndianOceanTerritory
import com.algolia.search.model.places.Country.BruneiDarussalam
import com.algolia.search.model.places.Country.Bulgaria
import com.algolia.search.model.places.Country.BurkinaFaso
import com.algolia.search.model.places.Country.Burundi
import com.algolia.search.model.places.Country.CaboVerde
import com.algolia.search.model.places.Country.Cambodia
import com.algolia.search.model.places.Country.Cameroon
import com.algolia.search.model.places.Country.Canada
import com.algolia.search.model.places.Country.CaribbeanNetherlands
import com.algolia.search.model.places.Country.CaymanIslands
import com.algolia.search.model.places.Country.CentralAfricanRepublic
import com.algolia.search.model.places.Country.Chad
import com.algolia.search.model.places.Country.Chile
import com.algolia.search.model.places.Country.China
import com.algolia.search.model.places.Country.ChristmasIsland
import com.algolia.search.model.places.Country.CocosIslands
import com.algolia.search.model.places.Country.Colombia
import com.algolia.search.model.places.Country.Comoros
import com.algolia.search.model.places.Country.CookIslands
import com.algolia.search.model.places.Country.CostaRica
import com.algolia.search.model.places.Country.Croatia
import com.algolia.search.model.places.Country.Cuba
import com.algolia.search.model.places.Country.Curacao
import com.algolia.search.model.places.Country.Cyprus
import com.algolia.search.model.places.Country.CzechRepublic
import com.algolia.search.model.places.Country.DemocraticRepublicOfTheCongo
import com.algolia.search.model.places.Country.Denmark
import com.algolia.search.model.places.Country.Djibouti
import com.algolia.search.model.places.Country.Dominica
import com.algolia.search.model.places.Country.DominicanRepublic
import com.algolia.search.model.places.Country.Ecuador
import com.algolia.search.model.places.Country.Egypt
import com.algolia.search.model.places.Country.ElSalvador
import com.algolia.search.model.places.Country.EquatorialGuinea
import com.algolia.search.model.places.Country.Eritrea
import com.algolia.search.model.places.Country.Estonia
import com.algolia.search.model.places.Country.Eswatini
import com.algolia.search.model.places.Country.Ethiopia
import com.algolia.search.model.places.Country.FalklandIslands
import com.algolia.search.model.places.Country.FaroeIslands
import com.algolia.search.model.places.Country.Fiji
import com.algolia.search.model.places.Country.Finland
import com.algolia.search.model.places.Country.France
import com.algolia.search.model.places.Country.FrenchGuiana
import com.algolia.search.model.places.Country.FrenchPolynesia
import com.algolia.search.model.places.Country.FrenchSouthernAndAntarcticLands
import com.algolia.search.model.places.Country.Gabon
import com.algolia.search.model.places.Country.Gambia
import com.algolia.search.model.places.Country.Georgia
import com.algolia.search.model.places.Country.Germany
import com.algolia.search.model.places.Country.Ghana
import com.algolia.search.model.places.Country.Gibraltar
import com.algolia.search.model.places.Country.Greece
import com.algolia.search.model.places.Country.Greenland
import com.algolia.search.model.places.Country.Grenada
import com.algolia.search.model.places.Country.Guadeloupe
import com.algolia.search.model.places.Country.Guam
import com.algolia.search.model.places.Country.Guatemala
import com.algolia.search.model.places.Country.Guinea
import com.algolia.search.model.places.Country.GuineaBissau
import com.algolia.search.model.places.Country.Guyana
import com.algolia.search.model.places.Country.Haiti
import com.algolia.search.model.places.Country.HeardIslandAndMcDonaldIslands
import com.algolia.search.model.places.Country.Honduras
import com.algolia.search.model.places.Country.HongKong
import com.algolia.search.model.places.Country.Hungary
import com.algolia.search.model.places.Country.Iceland
import com.algolia.search.model.places.Country.India
import com.algolia.search.model.places.Country.Indonesia
import com.algolia.search.model.places.Country.Iran
import com.algolia.search.model.places.Country.Iraq
import com.algolia.search.model.places.Country.Ireland
import com.algolia.search.model.places.Country.IsleOfMan
import com.algolia.search.model.places.Country.Israel
import com.algolia.search.model.places.Country.Italy
import com.algolia.search.model.places.Country.IvoryCoast
import com.algolia.search.model.places.Country.Jamaica
import com.algolia.search.model.places.Country.Japan
import com.algolia.search.model.places.Country.Jersey
import com.algolia.search.model.places.Country.Jordan
import com.algolia.search.model.places.Country.Kazakhstan
import com.algolia.search.model.places.Country.Kenya
import com.algolia.search.model.places.Country.Kiribati
import com.algolia.search.model.places.Country.Kuwait
import com.algolia.search.model.places.Country.Kyrgyzstan
import com.algolia.search.model.places.Country.Laos
import com.algolia.search.model.places.Country.Latvia
import com.algolia.search.model.places.Country.Lebanon
import com.algolia.search.model.places.Country.Lesotho
import com.algolia.search.model.places.Country.Liberia
import com.algolia.search.model.places.Country.Libya
import com.algolia.search.model.places.Country.Liechtenstein
import com.algolia.search.model.places.Country.Lithuania
import com.algolia.search.model.places.Country.Luxembourg
import com.algolia.search.model.places.Country.Macau
import com.algolia.search.model.places.Country.Madagascar
import com.algolia.search.model.places.Country.Malawi
import com.algolia.search.model.places.Country.Malaysia
import com.algolia.search.model.places.Country.Maldives
import com.algolia.search.model.places.Country.Mali
import com.algolia.search.model.places.Country.Malta
import com.algolia.search.model.places.Country.MarshallIslands
import com.algolia.search.model.places.Country.Martinique
import com.algolia.search.model.places.Country.Mauritania
import com.algolia.search.model.places.Country.Mauritius
import com.algolia.search.model.places.Country.Mayotte
import com.algolia.search.model.places.Country.Mexico
import com.algolia.search.model.places.Country.Micronesia
import com.algolia.search.model.places.Country.Moldova
import com.algolia.search.model.places.Country.Monaco
import com.algolia.search.model.places.Country.Mongolia
import com.algolia.search.model.places.Country.Montenegro
import com.algolia.search.model.places.Country.Montserrat
import com.algolia.search.model.places.Country.Morocco
import com.algolia.search.model.places.Country.Mozambique
import com.algolia.search.model.places.Country.Myanmar
import com.algolia.search.model.places.Country.Namibia
import com.algolia.search.model.places.Country.Nauru
import com.algolia.search.model.places.Country.Nepal
import com.algolia.search.model.places.Country.Netherlands
import com.algolia.search.model.places.Country.NewCaledonia
import com.algolia.search.model.places.Country.NewZealand
import com.algolia.search.model.places.Country.Nicaragua
import com.algolia.search.model.places.Country.Niger
import com.algolia.search.model.places.Country.Nigeria
import com.algolia.search.model.places.Country.Niue
import com.algolia.search.model.places.Country.NorfolkIsland
import com.algolia.search.model.places.Country.NorthKorea
import com.algolia.search.model.places.Country.NorthMacedonia
import com.algolia.search.model.places.Country.NorthernMarianaIslands
import com.algolia.search.model.places.Country.Norway
import com.algolia.search.model.places.Country.Oman
import com.algolia.search.model.places.Country.Pakistan
import com.algolia.search.model.places.Country.Palau
import com.algolia.search.model.places.Country.Palestine
import com.algolia.search.model.places.Country.Panama
import com.algolia.search.model.places.Country.PapuaNewGuinea
import com.algolia.search.model.places.Country.Paraguay
import com.algolia.search.model.places.Country.Peru
import com.algolia.search.model.places.Country.Philippines
import com.algolia.search.model.places.Country.PitcairnIslands
import com.algolia.search.model.places.Country.Poland
import com.algolia.search.model.places.Country.Portugal
import com.algolia.search.model.places.Country.PuertoRico
import com.algolia.search.model.places.Country.Qatar
import com.algolia.search.model.places.Country.RepublicOfTheCongo
import com.algolia.search.model.places.Country.Reunion
import com.algolia.search.model.places.Country.Romania
import com.algolia.search.model.places.Country.Russia
import com.algolia.search.model.places.Country.Rwanda
import com.algolia.search.model.places.Country.SaintBarthelemy
import com.algolia.search.model.places.Country.SaintHelena
import com.algolia.search.model.places.Country.SaintKittsAndNevis
import com.algolia.search.model.places.Country.SaintLucia
import com.algolia.search.model.places.Country.SaintMartin
import com.algolia.search.model.places.Country.SaintPierreAndMiquelon
import com.algolia.search.model.places.Country.SaintVincentAndTheGrenadines
import com.algolia.search.model.places.Country.Samoa
import com.algolia.search.model.places.Country.SanMarino
import com.algolia.search.model.places.Country.SaoTomeAndPrincipe
import com.algolia.search.model.places.Country.SaudiArabia
import com.algolia.search.model.places.Country.Senegal
import com.algolia.search.model.places.Country.Serbia
import com.algolia.search.model.places.Country.Seychelles
import com.algolia.search.model.places.Country.SierraLeone
import com.algolia.search.model.places.Country.Singapore
import com.algolia.search.model.places.Country.SintMaarten
import com.algolia.search.model.places.Country.Slovakia
import com.algolia.search.model.places.Country.Slovenia
import com.algolia.search.model.places.Country.SolomonIslands
import com.algolia.search.model.places.Country.Somalia
import com.algolia.search.model.places.Country.SouthAfrica
import com.algolia.search.model.places.Country.SouthGeorgiaAndTheSouthSandwichIslands
import com.algolia.search.model.places.Country.SouthKorea
import com.algolia.search.model.places.Country.SouthSudan
import com.algolia.search.model.places.Country.Spain
import com.algolia.search.model.places.Country.SriLanka
import com.algolia.search.model.places.Country.Sudan
import com.algolia.search.model.places.Country.Suriname
import com.algolia.search.model.places.Country.SvalbardAndJanMayen
import com.algolia.search.model.places.Country.Sweden
import com.algolia.search.model.places.Country.Switzerland
import com.algolia.search.model.places.Country.Syria
import com.algolia.search.model.places.Country.Taiwan
import com.algolia.search.model.places.Country.Tajikistan
import com.algolia.search.model.places.Country.Tanzania
import com.algolia.search.model.places.Country.Thailand
import com.algolia.search.model.places.Country.TimorLeste
import com.algolia.search.model.places.Country.Togo
import com.algolia.search.model.places.Country.Tokelau
import com.algolia.search.model.places.Country.Tonga
import com.algolia.search.model.places.Country.TrinidadAndTobago
import com.algolia.search.model.places.Country.Tunisia
import com.algolia.search.model.places.Country.Turkey
import com.algolia.search.model.places.Country.Turkmenistan
import com.algolia.search.model.places.Country.TurksAndCaicosIslands
import com.algolia.search.model.places.Country.Tuvalu
import com.algolia.search.model.places.Country.Uganda
import com.algolia.search.model.places.Country.Ukraine
import com.algolia.search.model.places.Country.UnitedArabEmirates
import com.algolia.search.model.places.Country.UnitedKingdom
import com.algolia.search.model.places.Country.UnitedStates
import com.algolia.search.model.places.Country.UnitedStatesMinorOutlyingIslands
import com.algolia.search.model.places.Country.Uruguay
import com.algolia.search.model.places.Country.Uzbekistan
import com.algolia.search.model.places.Country.Vanuatu
import com.algolia.search.model.places.Country.VaticanCity
import com.algolia.search.model.places.Country.Venezuela
import com.algolia.search.model.places.Country.Vietnam
import com.algolia.search.model.places.Country.VirginIslandsGB
import com.algolia.search.model.places.Country.VirginIslandsUS
import com.algolia.search.model.places.Country.WallisAndFutuna
import com.algolia.search.model.places.Country.WesternSahara
import com.algolia.search.model.places.Country.Yemen
import com.algolia.search.model.places.Country.Zambia
import com.algolia.search.model.places.Country.Zimbabwe
import com.algolia.search.serialize.internal.Countries
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestCountries : TestSerializer<Country>(Country) {

    override val items = listOf(
        Afghanistan to JsonPrimitive(Countries.Afghanistan),
        AlandIslands to JsonPrimitive(Countries.AlandIslands),
        Albania to JsonPrimitive(Countries.Albania),
        Algeria to JsonPrimitive(Countries.Algeria),
        AmericanSamoa to JsonPrimitive(Countries.AmericanSamoa),
        Andorra to JsonPrimitive(Countries.Andorra),
        Angola to JsonPrimitive(Countries.Angola),
        Anguilla to JsonPrimitive(Countries.Anguilla),
        Antarctica to JsonPrimitive(Countries.Antarctica),
        AntiguaAndBarbuda to JsonPrimitive(Countries.AntiguaAndBarbuda),
        Argentina to JsonPrimitive(Countries.Argentina),
        Armenia to JsonPrimitive(Countries.Armenia),
        Aruba to JsonPrimitive(Countries.Aruba),
        Australia to JsonPrimitive(Countries.Australia),
        Austria to JsonPrimitive(Countries.Austria),
        Azerbaijan to JsonPrimitive(Countries.Azerbaijan),
        Bahamas to JsonPrimitive(Countries.Bahamas),
        Bahrain to JsonPrimitive(Countries.Bahrain),
        Bangladesh to JsonPrimitive(Countries.Bangladesh),
        Barbados to JsonPrimitive(Countries.Barbados),
        Belarus to JsonPrimitive(Countries.Belarus),
        Belgium to JsonPrimitive(Countries.Belgium),
        Belize to JsonPrimitive(Countries.Belize),
        Benin to JsonPrimitive(Countries.Benin),
        Bermuda to JsonPrimitive(Countries.Bermuda),
        Bhutan to JsonPrimitive(Countries.Bhutan),
        Bolivia to JsonPrimitive(Countries.Bolivia),
        CaribbeanNetherlands to JsonPrimitive(Countries.CaribbeanNetherlands),
        BosniaAndHerzegovina to JsonPrimitive(Countries.BosniaAndHerzegovina),
        Botswana to JsonPrimitive(Countries.Botswana),
        BouvetIsland to JsonPrimitive(Countries.BouvetIsland),
        Brazil to JsonPrimitive(Countries.Brazil),
        BritishIndianOceanTerritory to JsonPrimitive(Countries.BritishIndianOceanTerritory),
        BruneiDarussalam to JsonPrimitive(Countries.BruneiDarussalam),
        Bulgaria to JsonPrimitive(Countries.Bulgaria),
        BurkinaFaso to JsonPrimitive(Countries.BurkinaFaso),
        Burundi to JsonPrimitive(Countries.Burundi),
        CaboVerde to JsonPrimitive(Countries.CaboVerde),
        Cambodia to JsonPrimitive(Countries.Cambodia),
        Cameroon to JsonPrimitive(Countries.Cameroon),
        Canada to JsonPrimitive(Countries.Canada),
        CaymanIslands to JsonPrimitive(Countries.CaymanIslands),
        CentralAfricanRepublic to JsonPrimitive(Countries.CentralAfricanRepublic),
        Chad to JsonPrimitive(Countries.Chad),
        Chile to JsonPrimitive(Countries.Chile),
        China to JsonPrimitive(Countries.China),
        ChristmasIsland to JsonPrimitive(Countries.ChristmasIsland),
        CocosIslands to JsonPrimitive(Countries.CocosIslands),
        Colombia to JsonPrimitive(Countries.Colombia),
        Comoros to JsonPrimitive(Countries.Comoros),
        RepublicOfTheCongo to JsonPrimitive(Countries.RepublicOfTheCongo),
        DemocraticRepublicOfTheCongo to JsonPrimitive(Countries.DemocraticRepublicOfTheCongo),
        CookIslands to JsonPrimitive(Countries.CookIslands),
        CostaRica to JsonPrimitive(Countries.CostaRica),
        IvoryCoast to JsonPrimitive(Countries.IvoryCoast),
        Croatia to JsonPrimitive(Countries.Croatia),
        Cuba to JsonPrimitive(Countries.Cuba),
        Curacao to JsonPrimitive(Countries.Curacao),
        Cyprus to JsonPrimitive(Countries.Cyprus),
        CzechRepublic to JsonPrimitive(Countries.CzechRepublic),
        Denmark to JsonPrimitive(Countries.Denmark),
        Djibouti to JsonPrimitive(Countries.Djibouti),
        Dominica to JsonPrimitive(Countries.Dominica),
        DominicanRepublic to JsonPrimitive(Countries.DominicanRepublic),
        Ecuador to JsonPrimitive(Countries.Ecuador),
        Egypt to JsonPrimitive(Countries.Egypt),
        ElSalvador to JsonPrimitive(Countries.ElSalvador),
        EquatorialGuinea to JsonPrimitive(Countries.EquatorialGuinea),
        Eritrea to JsonPrimitive(Countries.Eritrea),
        Estonia to JsonPrimitive(Countries.Estonia),
        Eswatini to JsonPrimitive(Countries.Eswatini),
        Ethiopia to JsonPrimitive(Countries.Ethiopia),
        FalklandIslands to JsonPrimitive(Countries.FalklandIslands),
        FaroeIslands to JsonPrimitive(Countries.FaroeIslands),
        Fiji to JsonPrimitive(Countries.Fiji),
        Finland to JsonPrimitive(Countries.Finland),
        France to JsonPrimitive(Countries.France),
        FrenchGuiana to JsonPrimitive(Countries.FrenchGuiana),
        FrenchPolynesia to JsonPrimitive(Countries.FrenchPolynesia),
        FrenchSouthernAndAntarcticLands to JsonPrimitive(Countries.FrenchSouthernAndAntarcticLands),
        Gabon to JsonPrimitive(Countries.Gabon),
        Gambia to JsonPrimitive(Countries.Gambia),
        Georgia to JsonPrimitive(Countries.Georgia),
        Germany to JsonPrimitive(Countries.Germany),
        Ghana to JsonPrimitive(Countries.Ghana),
        Gibraltar to JsonPrimitive(Countries.Gibraltar),
        Greece to JsonPrimitive(Countries.Greece),
        Greenland to JsonPrimitive(Countries.Greenland),
        Grenada to JsonPrimitive(Countries.Grenada),
        Guadeloupe to JsonPrimitive(Countries.Guadeloupe),
        Guam to JsonPrimitive(Countries.Guam),
        Guatemala to JsonPrimitive(Countries.Guatemala),
        BailiwickOfGuernsey to JsonPrimitive(Countries.BailiwickOfGuernsey),
        Guinea to JsonPrimitive(Countries.Guinea),
        GuineaBissau to JsonPrimitive(Countries.GuineaBissau),
        Guyana to JsonPrimitive(Countries.Guyana),
        Haiti to JsonPrimitive(Countries.Haiti),
        HeardIslandAndMcDonaldIslands to JsonPrimitive(Countries.HeardIslandAndMcDonaldIslands),
        VaticanCity to JsonPrimitive(Countries.VaticanCity),
        Honduras to JsonPrimitive(Countries.Honduras),
        HongKong to JsonPrimitive(Countries.HongKong),
        Hungary to JsonPrimitive(Countries.Hungary),
        Iceland to JsonPrimitive(Countries.Iceland),
        India to JsonPrimitive(Countries.India),
        Indonesia to JsonPrimitive(Countries.Indonesia),
        Iran to JsonPrimitive(Countries.Iran),
        Iraq to JsonPrimitive(Countries.Iraq),
        Ireland to JsonPrimitive(Countries.Ireland),
        IsleOfMan to JsonPrimitive(Countries.IsleOfMan),
        Israel to JsonPrimitive(Countries.Israel),
        Italy to JsonPrimitive(Countries.Italy),
        Jamaica to JsonPrimitive(Countries.Jamaica),
        Japan to JsonPrimitive(Countries.Japan),
        Jersey to JsonPrimitive(Countries.Jersey),
        Jordan to JsonPrimitive(Countries.Jordan),
        Kazakhstan to JsonPrimitive(Countries.Kazakhstan),
        Kenya to JsonPrimitive(Countries.Kenya),
        Kiribati to JsonPrimitive(Countries.Kiribati),
        NorthKorea to JsonPrimitive(Countries.NorthKorea),
        SouthKorea to JsonPrimitive(Countries.SouthKorea),
        Kuwait to JsonPrimitive(Countries.Kuwait),
        Kyrgyzstan to JsonPrimitive(Countries.Kyrgyzstan),
        Laos to JsonPrimitive(Countries.Laos),
        Latvia to JsonPrimitive(Countries.Latvia),
        Lebanon to JsonPrimitive(Countries.Lebanon),
        Lesotho to JsonPrimitive(Countries.Lesotho),
        Liberia to JsonPrimitive(Countries.Liberia),
        Libya to JsonPrimitive(Countries.Libya),
        Liechtenstein to JsonPrimitive(Countries.Liechtenstein),
        Lithuania to JsonPrimitive(Countries.Lithuania),
        Luxembourg to JsonPrimitive(Countries.Luxembourg),
        Macau to JsonPrimitive(Countries.Macau),
        Madagascar to JsonPrimitive(Countries.Madagascar),
        Malawi to JsonPrimitive(Countries.Malawi),
        Malaysia to JsonPrimitive(Countries.Malaysia),
        Maldives to JsonPrimitive(Countries.Maldives),
        Mali to JsonPrimitive(Countries.Mali),
        Malta to JsonPrimitive(Countries.Malta),
        MarshallIslands to JsonPrimitive(Countries.MarshallIslands),
        Martinique to JsonPrimitive(Countries.Martinique),
        Mauritania to JsonPrimitive(Countries.Mauritania),
        Mauritius to JsonPrimitive(Countries.Mauritius),
        Mayotte to JsonPrimitive(Countries.Mayotte),
        Mexico to JsonPrimitive(Countries.Mexico),
        Micronesia to JsonPrimitive(Countries.Micronesia),
        Moldova to JsonPrimitive(Countries.Moldova),
        Monaco to JsonPrimitive(Countries.Monaco),
        Mongolia to JsonPrimitive(Countries.Mongolia),
        Montenegro to JsonPrimitive(Countries.Montenegro),
        Montserrat to JsonPrimitive(Countries.Montserrat),
        Morocco to JsonPrimitive(Countries.Morocco),
        Mozambique to JsonPrimitive(Countries.Mozambique),
        Myanmar to JsonPrimitive(Countries.Myanmar),
        Namibia to JsonPrimitive(Countries.Namibia),
        Nauru to JsonPrimitive(Countries.Nauru),
        Nepal to JsonPrimitive(Countries.Nepal),
        Netherlands to JsonPrimitive(Countries.Netherlands),
        NewCaledonia to JsonPrimitive(Countries.NewCaledonia),
        NewZealand to JsonPrimitive(Countries.NewZealand),
        Nicaragua to JsonPrimitive(Countries.Nicaragua),
        Niger to JsonPrimitive(Countries.Niger),
        Nigeria to JsonPrimitive(Countries.Nigeria),
        Niue to JsonPrimitive(Countries.Niue),
        NorfolkIsland to JsonPrimitive(Countries.NorfolkIsland),
        NorthMacedonia to JsonPrimitive(Countries.NorthMacedonia),
        NorthernMarianaIslands to JsonPrimitive(Countries.NorthernMarianaIslands),
        Norway to JsonPrimitive(Countries.Norway),
        Oman to JsonPrimitive(Countries.Oman),
        Pakistan to JsonPrimitive(Countries.Pakistan),
        Palau to JsonPrimitive(Countries.Palau),
        Palestine to JsonPrimitive(Countries.Palestine),
        Panama to JsonPrimitive(Countries.Panama),
        PapuaNewGuinea to JsonPrimitive(Countries.PapuaNewGuinea),
        Paraguay to JsonPrimitive(Countries.Paraguay),
        Peru to JsonPrimitive(Countries.Peru),
        Philippines to JsonPrimitive(Countries.Philippines),
        PitcairnIslands to JsonPrimitive(Countries.PitcairnIslands),
        Poland to JsonPrimitive(Countries.Poland),
        Portugal to JsonPrimitive(Countries.Portugal),
        PuertoRico to JsonPrimitive(Countries.PuertoRico),
        Qatar to JsonPrimitive(Countries.Qatar),
        Reunion to JsonPrimitive(Countries.Reunion),
        Romania to JsonPrimitive(Countries.Romania),
        Russia to JsonPrimitive(Countries.Russia),
        Rwanda to JsonPrimitive(Countries.Rwanda),
        SaintBarthelemy to JsonPrimitive(Countries.SaintBarthelemy),
        SaintHelena to JsonPrimitive(Countries.SaintHelena),
        SaintKittsAndNevis to JsonPrimitive(Countries.SaintKittsAndNevis),
        SaintLucia to JsonPrimitive(Countries.SaintLucia),
        SaintMartin to JsonPrimitive(Countries.SaintMartin),
        SaintPierreAndMiquelon to JsonPrimitive(Countries.SaintPierreAndMiquelon),
        SaintVincentAndTheGrenadines to JsonPrimitive(Countries.SaintVincentAndTheGrenadines),
        Samoa to JsonPrimitive(Countries.Samoa),
        SanMarino to JsonPrimitive(Countries.SanMarino),
        SaoTomeAndPrincipe to JsonPrimitive(Countries.SaoTomeAndPrincipe),
        SaudiArabia to JsonPrimitive(Countries.SaudiArabia),
        Senegal to JsonPrimitive(Countries.Senegal),
        Serbia to JsonPrimitive(Countries.Serbia),
        Seychelles to JsonPrimitive(Countries.Seychelles),
        SierraLeone to JsonPrimitive(Countries.SierraLeone),
        Singapore to JsonPrimitive(Countries.Singapore),
        SintMaarten to JsonPrimitive(Countries.SintMaarten),
        Slovakia to JsonPrimitive(Countries.Slovakia),
        Slovenia to JsonPrimitive(Countries.Slovenia),
        SolomonIslands to JsonPrimitive(Countries.SolomonIslands),
        Somalia to JsonPrimitive(Countries.Somalia),
        SouthAfrica to JsonPrimitive(Countries.SouthAfrica),
        SouthGeorgiaAndTheSouthSandwichIslands to JsonPrimitive(Countries.SouthGeorgiaAndTheSouthSandwichIslands),
        SouthSudan to JsonPrimitive(Countries.SouthSudan),
        Spain to JsonPrimitive(Countries.Spain),
        SriLanka to JsonPrimitive(Countries.SriLanka),
        Sudan to JsonPrimitive(Countries.Sudan),
        Suriname to JsonPrimitive(Countries.Suriname),
        SvalbardAndJanMayen to JsonPrimitive(Countries.SvalbardAndJanMayen),
        Sweden to JsonPrimitive(Countries.Sweden),
        Switzerland to JsonPrimitive(Countries.Switzerland),
        Syria to JsonPrimitive(Countries.Syria),
        Taiwan to JsonPrimitive(Countries.Taiwan),
        Tajikistan to JsonPrimitive(Countries.Tajikistan),
        Tanzania to JsonPrimitive(Countries.Tanzania),
        Thailand to JsonPrimitive(Countries.Thailand),
        TimorLeste to JsonPrimitive(Countries.TimorLeste),
        Togo to JsonPrimitive(Countries.Togo),
        Tokelau to JsonPrimitive(Countries.Tokelau),
        Tonga to JsonPrimitive(Countries.Tonga),
        TrinidadAndTobago to JsonPrimitive(Countries.TrinidadAndTobago),
        Tunisia to JsonPrimitive(Countries.Tunisia),
        Turkey to JsonPrimitive(Countries.Turkey),
        Turkmenistan to JsonPrimitive(Countries.Turkmenistan),
        TurksAndCaicosIslands to JsonPrimitive(Countries.TurksAndCaicosIslands),
        Tuvalu to JsonPrimitive(Countries.Tuvalu),
        Uganda to JsonPrimitive(Countries.Uganda),
        Ukraine to JsonPrimitive(Countries.Ukraine),
        UnitedArabEmirates to JsonPrimitive(Countries.UnitedArabEmirates),
        UnitedKingdom to JsonPrimitive(Countries.UnitedKingdom),
        UnitedStates to JsonPrimitive(Countries.UnitedStates),
        UnitedStatesMinorOutlyingIslands to JsonPrimitive(Countries.UnitedStatesMinorOutlyingIslands),
        Uruguay to JsonPrimitive(Countries.Uruguay),
        Uzbekistan to JsonPrimitive(Countries.Uzbekistan),
        Vanuatu to JsonPrimitive(Countries.Vanuatu),
        Venezuela to JsonPrimitive(Countries.Venezuela),
        Vietnam to JsonPrimitive(Countries.Vietnam),
        VirginIslandsGB to JsonPrimitive(Countries.VirginIslandsGB),
        VirginIslandsUS to JsonPrimitive(Countries.VirginIslandsUS),
        WallisAndFutuna to JsonPrimitive(Countries.WallisAndFutuna),
        WesternSahara to JsonPrimitive(Countries.WesternSahara),
        Yemen to JsonPrimitive(Countries.Yemen),
        Zambia to JsonPrimitive(Countries.Zambia),
        Zimbabwe to JsonPrimitive(Countries.Zimbabwe)
    )
}
