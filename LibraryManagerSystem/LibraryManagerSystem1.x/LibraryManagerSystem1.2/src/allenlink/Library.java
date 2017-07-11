	
	/**
	 * 主窗体。
	 */
	package allenlink;

	import java.awt.BorderLayout;
	import java.awt.event.ActionEvent;
	
	import javax.swing.Action;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenuBar;
	import javax.swing.JToolBar;
	import javax.swing.WindowConstants;
	
	public class Library extends JFrame{
		
		public Library(){
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setLocationByPlatform(true);
			this.setSize(800, 600);
			JMenuBar menuBar=createMenu();
			setJMenuBar(menuBar);
			JToolBar toolBar=createBar();
			this.getContentPane().add(toolBar,BorderLayout.NORTH);
			final JLabel label=new JLabel();
			
			//为桌面面板添加组件监听事件
			
			
		}

		
		private JToolBar createBar() {
			
			return null;
		}


		private JMenuBar createMenu() {
			
			return null;
		}

		
	}
