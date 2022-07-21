/* See LICENSE file for copyright and license details. */
package libgamma;


/**
 * Types for connectors
 */
public enum ConnectorType
{
	/**
	 * The adjustment method does not know the connector's type
	 * <p>
	 * (This could be considered an error)
	 */
	Unknown(0),

	/**
	 * Video Graphics Array (VGA)
	 */
	VGA(1),

	/**
	 * Digital Visual Interface, unknown type
	 */
	DVI(2),

	/**
	 * Digital Visual Interface, integrated (DVI-I)
	 */
	DVII(3),

	/**
	 * Digital Visual Interface, digital only (DVI-D)
	 */
	DVID(4),

	/**
	 * Digital Visual Interface, analogue only (DVI-A)
	 */
	DVIA(5),

	/**
	 * Composite video
	 */
	Composite(6),

	/**
	 * Separate Video (S-video)
	 */
	SVIDEO(7),

	/**
	 * Low-voltage differential signaling (LVDS)
	 */
	LVDS(8),

	/**
	 * Component video, usually separate cables for each channel
	 */
	Component(9),

	/**
	 * 9 pin DIN (Deutsches Institut für Normung) connector
	 */
	NinePinDIN(10),

	/**
	 * DisplayPort
	 */
	DisplayPort(11),

	/**
	 * High-Definition Multimedia Interface (HDMI), unknown type
	 */
	HDMI(12),

	/**
	 * High-Definition Multimedia Interface, type A (HDMI-A)
	 */
	HDMIA(13),

	/**
	 * High-Definition Multimedia Interface, type B (HDMI-B)
	 */
	HDMIB(14),

	/**
	 * Television, unknown connector
	 */
	TV(15),

	/**
	 * Embedded DisplayPort (eDP)
	 */
	eDP(16),

	/**
	 * A virtual connector
	 */
	VIRTUAL(17),

	/**
	 * Display Serial Interface (DSI)
	 */
	DSI(18),

	/**
	 * LFP connector
	 * <p>
	 * (If you know what this is add it to Wikipedia)
	 */
	LFP(19);


	/**
	 * Type initialiser
	 */
	static
	{
		Libgamma.initialise();
	}



	/**
	 * Subpixel orders by their numerical values
	 */
	public static ConnectorType[] VALUES =
	{
		Unknown, VGA, DVI, DVII, DVID, DVIA, Composite, SVIDEO, LVDS, Component,
		NinePinDIN, DisplayPort, HDMI, HDMIA, HDMIB, TV, eDP, VIRTUAL, DSI, LFP
	};

	/**
	 * Constructor
	 * 
	 * @param  value  The numerical value of the connector type
	 */
	private ConnectorType(int value)
	{
		this.value = value;
	}

	/**
	 * The numerical value of the connector type
	 */
	public int value;
}
