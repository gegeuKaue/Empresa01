/*
 * 
 */
package br.com.contmatic.telefone;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author geovane.santos
 */
/**
 * The Enum TelefoneDDD.
 */
public enum TelefoneDDD {

	/** The ddd11. */
	DDD11(11, "são paulo | região metropolitana de são paulo."),

	/** The ddd12. */
	DDD12(12, "são paulo | são josé dos campos e região."),

	/** The ddd13. */
	DDD13(13, "são paulo | região metropolitana da baixada santista."),

	/** The ddd14. */
	DDD14(14, "são paulo | bauru, jaú, marília, botucatu e região."),

	/** The ddd15. */
	DDD15(15, "são paulo | sorocaba e região."),

	/** The ddd16. */
	DDD16(16, "são paulo | ribeirão preto, são carlos, araraquara e região."),

	/** The ddd17. */
	DDD17(17, "são paulo | são josé do rio preto e região."),

	/** The ddd18. */
	DDD18(18, "são paulo | presidente prudente, araçatuba e região."),

	/** The ddd19. */
	DDD19(19, "são paulo | região metropolitana de campinas."),

	/** The ddd22. */
	DDD22(22, "rio de janeiro | campos dos goytacazes e região."),

	/** The ddd21. */
	DDD21(21, "rio de janeiro | região metropolitana do rio de janeiro."),

	/** The ddd24. */
	DDD24(24, "rio de janeiro | volta redonda, petrópolis e região."),

	/** The ddd27. */
	DDD27(27, "espírito santo | região metropolitana de vitória."),

	/** The ddd28. */
	DDD28(28, "espírito santo | cachoeiro de itapemirim e região."),

	/** The ddd31. */
	DDD31(31, "minas gerais | região metropolitana de belo horizonte "),

	/** The ddd32. */
	DDD32(32, "minas gerais | juiz de fora e região."),

	/** The ddd33. */
	DDD33(33, "minas gerais | governador valadares e região."),

	/** The ddd34. */
	DDD34(34, "minas gerais | uberlândia e região."),

	/** The ddd35. */
	DDD35(35, "minas gerais | poços de caldas, pouso alegre, varginha e região."),

	/** The ddd37. */
	DDD37(37, "minas gerais | divinópolis, itaúna e região."),

	/** The ddd38. */
	DDD38(38, "minas gerais | montes claros e região."),

	/** The ddd41. */
	DDD41(41, "Paraná | região metropolitana de curitiba."),

	/** The ddd42. */
	DDD42(42, "paraná | ponta grossa e região."),

	/** The ddd43. */
	DDD43(43, "paraná | londrina e região."),

	/** The ddd44. */
	DDD44(44, "paraná | maringá e região."),

	/** The ddd45. */
	DDD45(45, "paraná | cascavel e região."),

	/** The ddd46. */
	DDD46(46, "paraná | francisco beltrão, pato branco e região."),

	/** The ddd47. */
	DDD47(47, "santa catarina | joinville, blumenau, balneário camboriú e região."),

	/** The ddd48. */
	DDD48(48, "santa catarina | região metropolitana de florianópolis e criciúma."),

	/** The ddd49. */
	DDD49(49, "santa catarina | chapecó, lages e região."),

	/** The ddd51. */
	DDD51(51, "rio grande do sul | região metropolitana de porto alegre."),

	/** The ddd53. */
	DDD53(53, "rio grande do sul | pelotas e região."),

	/** The ddd54. */
	DDD54(54, "rio grande do sul | caxias do sul e região."),

	/** The ddd55. */
	DDD55(55, "rio grande do sul | santa maria e região."),

	/** The ddd61. */
	DDD61(61, "distrito federal e goiás | brasília e região."),

	/** The ddd62. */
	DDD62(62, "goiás | região metropolitana de goiânia."),

	/** The ddd63. */
	DDD63(63, "tocantins | todos os municípios do estado."),

	/** The ddd64. */
	DDD64(64, "goiás | rio verde e região."),

	/** The ddd65. */
	DDD65(65, "mato grosso | região metropolitana de cuiabá."),

	/** The ddd66. */
	DDD66(66, "mato grosso | mato grosso do sul. "),

	/** The ddd67. */
	DDD67(67, "mato grosso do sul | todos os municípios do estado."),

	/** The ddd68. */
	DDD68(68, "acre | todos os municípios do estado."),

	/** The ddd69. */
	DDD69(69, "rondônia | todos os municípios do estado."),

	/** The ddd71. */
	DDD71(71, "bahia | região metropolitana de salvador."),

	/** The ddd73. */
	DDD73(73, "bahia | itabuna, ilhéus e região."),

	/** The ddd74. */
	DDD74(74, "bahia | juazeiro e região."),

	/** The ddd75. */
	DDD75(75, "bahia | feira de santana e região."),

	/** The ddd77. */
	DDD77(77, "bahia | vitória da conquista e região."),

	/** The ddd79. */
	DDD79(79, "sergipe | todos os municípios do estado."),

	/** The ddd81. */
	DDD81(81, "pernambuco | região metropolitana de recife."),

	/** The ddd82. */
	DDD82(82, "alagoas | todos os municípios do estado."),

	/** The ddd83. */
	DDD83(83, "paraíba | todos os municípios do estado."),

	/** The ddd84. */
	DDD84(84, "rio grande do norte | todos os municípios do estado."),

	/** The ddd85. */
	DDD85(85, "ceará | região metropolitana de fortaleza."),

	/** The ddd88. */
	DDD88(88, "ceará | região de juazeiro do norte."),

	/** The ddd86. */
	DDD86(86, "piauí | região de teresina."),

	/** The ddd89. */
	DDD89(89, "piauí | região de picos e floriano."),

	/** The ddd87. */
	DDD87(87, "pernambuco | região de petrolina."),

	/** The ddd91. */
	DDD91(91, "pará | região metropolitana de belém."),

	/** The ddd93. */
	DDD93(93, "pará | região de santarém."),

	/** The ddd94. */
	DDD94(94, "pará | região de marabá."),

	/** The ddd92. */
	DDD92(92, "amazonas | região de manaus."),

	/** The ddd97. */
	DDD97(97, "amazonas | região de tefé e coari."),

	/** The ddd95. */
	DDD95(95, "roraima |todos os municípios do estado."),

	/** The ddd96. */
	DDD96(96, "amapá | todos os municípios do estado."),

	/** The ddd98. */
	DDD98(98, "maranhão | região metropolitana de são luís."),

	/** The ddd99. */
	DDD99(99, "maranhão | região de imperatriz.");

	/** The ddd. */
	private String regiao;

	/** The ddd. */
	private int ddd;

	/**
	 * Instantiates a new telefone DDD.
	 *
	 * @param ddd    the ddd
	 * @param regiao the regiao
	 */
	private TelefoneDDD(int ddd, String regiao) {
		this.regiao = regiao;
		this.ddd = ddd;
	}

	/**
	 * Gets the regiao.
	 *
	 * @return the regiao
	 */
	public String getRegiao() {
		return regiao;
	}

	/**
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public int getDdd() {
		return ddd;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}