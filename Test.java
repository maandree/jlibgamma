/* See LICENSE file for copyright and license details. */
import libgamma.*;


/**
 * jlibgamma interactive test class
 */
public class Test
{
	public static void p(boolean text)  { System.out.println(text); }
	public static void p(byte    text)  { System.out.println(text); }
	public static void p(short   text)  { System.out.println(text); }
	public static void p(int     text)  { System.out.println(text); }
	public static void p(long    text)  { System.out.println(text); }
	public static void p(char    text)  { System.out.println(text); }
	public static void p(String  text)  { System.out.println(text); }
	public static void p(Object  text)  { System.out.println(text); }
	public static void p()              { System.out.println();     }

	public static void P(boolean text)  { System.out.print(text + " "); }
	public static void P(byte    text)  { System.out.print(text + " "); }
	public static void P(short   text)  { System.out.print(text + " "); }
	public static void P(int     text)  { System.out.print(text + " "); }
	public static void P(long    text)  { System.out.print(text + " "); }
	public static void P(char    text)  { System.out.print(text + " "); }
	public static void P(String  text)  { System.out.print(text + " "); }
	public static void P(Object  text)  { System.out.print(text + " "); }


	/**
	 * Main test function
	 * 
	 * @param  args  Command line arguments, excluding $0
	 */
	public static void main(String[] args) throws LibgammaException
	{
		p(LibgammaException.name_of_error(-3));
		p(LibgammaException.value_of_error("LIBGAMMA_NO_SUCH_SITE"));
		p();

		LibgammaException err = new LibgammaException(LibgammaException.LIBGAMMA_DEVICE_REQUIRE_GROUP);
		p(err.error_code);
		p(err.group_gid);
		p(err.group_name);
		p(err.string);
		err = new LibgammaException(5);
		p(err.string);
		p(err);
		p();

		for (AdjustmentMethod method : AdjustmentMethod.list_methods(0))  P(method.value);  p();
		for (AdjustmentMethod method : AdjustmentMethod.list_methods(1))  P(method.value);  p();
		for (AdjustmentMethod method : AdjustmentMethod.list_methods(2))  P(method.value);  p();
		for (AdjustmentMethod method : AdjustmentMethod.list_methods(3))  P(method.value);  p();
		for (AdjustmentMethod method : AdjustmentMethod.list_methods(4))  P(method.value);  p();
		p();

		AdjustmentMethod method = AdjustmentMethod.list_methods(0)[0];
		p(method);
		p(SubpixelOrder.HORIZONTAL_RGB);
		p(ConnectorType.VGA);
		p();

		p(AdjustmentMethod.X_RANDR.is_available());
		p(AdjustmentMethod.X_RANDR.get_default_site());
		p(AdjustmentMethod.X_RANDR.get_default_site_variable());
		p();

		for (byte b : CRTCInformation.unhex("0123456789abcdef"))
			P(b & 255);
		p();
		p(CRTCInformation.behex(CRTCInformation.unhex("0123456789abcdef")));
		p();

		Site site = new Site(method, ":0");
		p(site.partitions_available);
		p(site);
		Partition partition = new Partition(site, 0);
		p(partition.crtcs_available);
		p(partition);
		CRTC crtc = new CRTC(partition, 0);
		p(crtc);
		p();

		CRTCInformation info = crtc.get_information(~0);
		p(info);
		p();

		AdjustmentMethodCapabilities caps = method.get_capabilities();
		p(caps);
		p();

		GammaRamps<Ramp16> ramps = new GammaRamps<Ramp16>(info.red_gamma_size,
		                                                  info.green_gamma_size,
		                                                  info.blue_gamma_size,
		                                                  16);

		int[] saved_red = new int[ramps.red.size];
		int[] saved_green = new int[ramps.green.size];
		int[] saved_blue = new int[ramps.blue.size];

		crtc.get_gamma(ramps);

		for (int i = 0; i < ramps.red.size; i++) {
			P(ramps.red.get(i));
			ramps.red.set(i, (saved_red[i] = ramps.red.get(i)) / 2);
		}
		p();
		p();
		for (int i = 0; i < ramps.green.size; i++) {
			P(ramps.green.get(i));
			ramps.green.set(i, (saved_green[i] = ramps.green.get(i)) / 2);
		}
		p();
		p();
		for (int i = 0; i < ramps.blue.size; i++) {
			P(ramps.blue.get(i));
			ramps.blue.set(i, (saved_blue[i] = ramps.blue.get(i)) / 2);
		}
		p();
		p();

		crtc.set_gamma(ramps);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException _error) {
			/* ignore. */
		}

		for (int i = 0; i < ramps.red.  size; i++)  ramps.red.  set(i, saved_red[i]);
		for (int i = 0; i < ramps.green.size; i++)  ramps.green.set(i, saved_green[i]);
		for (int i = 0; i < ramps.blue. size; i++)  ramps.blue. set(i, saved_blue[i]);

		crtc.set_gamma(ramps);

		ramps.close();
		crtc.close();
		partition.close();
		site.close();

		try {
			throw new LibgammaException(0);
		} catch (LibgammaException error) {
			p(error);
		}
	}
}
