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
