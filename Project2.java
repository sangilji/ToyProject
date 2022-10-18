package ToyProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import se.datadosen.component.RiverLayout;



public class Project2 {

	Connection conn;
	Calendar cal = Calendar.getInstance();

	int year = cal.get(cal.YEAR);
	int month =cal.get(cal.MONTH)+1; // 0이 1월이므로 +1
	int date =cal.get(cal.DATE);
	int lastDay = cal.getActualMaximum(Calendar.DATE);
	int yoil;
	int ALLPrice;
	int Old;
	int Adult;
	int Child;
	int SeatData[] = new int[5];
	int count;
	boolean SeatCheck[] = new boolean [16];
	boolean S_StationCheck;
	boolean L_StationCheck;
	boolean DateCheck;
	boolean TimeCheck;

	ArrayList<String> CalArr = new ArrayList<>();

	JFrame frame = new JFrame("시외버스터미널 예약프로그램");
	JFrame LoginFrame = new JFrame("로그인 화면");
	JFrame NewFrame = new JFrame("회원가입 화면");
	JFrame ReservationFrame = new JFrame("예매");
	JFrame UsefulFrame = new JFrame("이용안내/문의");
	JFrame MyPageFrame = new JFrame("마이페이지");
	JFrame TicketCheckFrame = new JFrame("예매 확인 및 변경");
	JFrame TicketCheckFrame2 = new JFrame("예매 확인 및 변경");
	JFrame S_StationSelectFrame = new JFrame("출발지");
	JFrame L_StationSelectFrame = new JFrame("도착지");
	JFrame DateFrame = new JFrame("날짜조회");

	JPanel MainPanel;
	JPanel loginPanel;
	JPanel newPanel;
	JPanel reservationPanel;
	JPanel usefulPanel;
	JPanel centerPanel;
	JPanel terminalPanel;
	JPanel companyPanel;
	JPanel myPagePanel;
	JPanel ticketCheckPanel;
	JPanel ticketCheckPanel2;
	JPanel s_StationSelectPanel;
	JPanel l_StationSelectPanel;
	JPanel inquiryPanel;
	JPanel DatePanel;
	JPanel seatPanel;
	JPanel seatCoverPanel;
	JPanel memberPanel;

	JTextField ID;
	JPasswordField password;

	JTextField phone;
	JTextField birthday;

	JTextField NewID;
	JTextField NewPassword;
	JTextField NewName;
	JTextField NewPhone;
	JTextField NewBirthday;

	JTextField M_name;
	JTextField M_ID;
	JTextField M_Password;
	JTextField M_Phone;
	JTextField M_Number;
	JTextField M_Birthday;

	JTextField S_Station;
	JTextField L_Station;
	JTextField Date;

	JTextField BusNumber;
	JTextField S_Time;
	JTextField L_Time;

	JTextField ALL_num;
	JTextField O_num;
	JTextField A_num;
	JTextField C_num;
	JTextField Price;
	
	JTextField T_id;


	String IDCode;
	String PasswordCode;
	String HomePage = "https://txbus.t-money.co.kr/main.do";

	Dimension size = new Dimension();


	JScrollPane cScroller;
	JScrollPane cScroller1;
	JScrollPane cScroller2;
	JScrollPane cScroller3;
	JScrollPane cScroller4;
	JScrollPane cScroller5;
	JScrollPane cScroller6;

	JLayeredPane lp = new JLayeredPane();
	JLayeredPane lp1 = new JLayeredPane();
	JLayeredPane lp2 = new JLayeredPane();
	GridLayout gridLayout = new GridLayout(CalArr.size()/7,7);

	ImageIcon SeatEmpty = new ImageIcon("src/좌석.png");
	ImageIcon SeatSelect = new ImageIcon("src/좌석선택.png");


	PosImageIcon Start = new PosImageIcon("src/출발지.png",-10,50,240,155);
	PosImageIcon Last = new PosImageIcon("src/도착지.png",230,50,240,155);
	PosImageIcon DateImage = new PosImageIcon("src/가는날.png",-10,205,480,110);
	PosImageIcon main1 = new PosImageIcon("src/main1.png",0,0,480,180);
	PosImageIcon main2 = new PosImageIcon("src/main2.png",0,180,480,180);
	PosImageIcon main3 = new PosImageIcon("src/main3.png",0,360,480,180);
	PosImageIcon main4 = new PosImageIcon("src/main4.png",0,540,120,160);
	PosImageIcon main5 = new PosImageIcon("src/main5.png",120,540,120,160);
	PosImageIcon main6 = new PosImageIcon("src/main6.png",240,540,120,160);
	PosImageIcon main7 = new PosImageIcon("src/main7.png",360,540,120,160);
	PosImageIcon loginBackground = new PosImageIcon("src/loginBackground.png",0,0,480,720);
	PosImageIcon mainBackground = new PosImageIcon("src/mainBackground.png",0,0,480,720);
	PosImageIcon TicketCheckBackground = new PosImageIcon("src/TicketCheckBackground.png",0,0,480,60);
	PosImageIcon background = new PosImageIcon("src/background3.png",0,0,480,720);
	PosImageIcon memberBackground = new PosImageIcon("src/인원배경.png",0,0,480,720);
	PosImageIcon noTicket= new PosImageIcon("src/내역.png",0,60,480,680);
	PosImageIcon location= new PosImageIcon("src/출발도착.png",10,60,45,100);
	PosImageIcon StationBackground = new PosImageIcon("src/StationBackground.png",0,0,480,210);
	PosImageIcon SeatBackground = new PosImageIcon("src/좌석배경.png",0,0,470,1100);


	JButton mainButton1 = new JButton("");
	JButton mainButton2 = new JButton("");
	JButton mainButton3 = new JButton("");
	JButton mainButton4 = new JButton("");
	JButton mainButton5 = new JButton("");
	JButton mainButton6 = new JButton("");
	JButton mainButton7 = new JButton("");
	JButton login = new JButton("login");
	JButton newButton = new JButton("new");
	JButton OK = new JButton("OK");
	JButton inquiry = new JButton("조회하기");
	JButton Exit = new JButton("Exit");
	JButton Save = new JButton("Save");
	JButton S_OK = new JButton("OK");
	JButton L_OK = new JButton("OK");
	JButton Seat_OK = new JButton("OK");
	JButton SeatButton = new JButton("좌석조회");
	JButton FinishReservation = new JButton("예약완료");

	JButton firstButton = new JButton("");
	JButton lastButton = new JButton("");
	JButton startDateButton = new JButton("");
	JButton gradeButton = new JButton(new ImageIcon("src/등급.png"));
	JButton centerButton = new JButton(new ImageIcon("src/center.png"));
	JButton terminalButton = new JButton(new ImageIcon("src/terminal.png"));
	JButton companyButton = new JButton(new ImageIcon("src/company.png"));
	JButton logoutButton = new JButton(new ImageIcon("src/logout.png"));
	JButton CalButton = new JButton(new ImageIcon("src/달력.png"));
	JButton O_Plus = new JButton(new ImageIcon("src/더하기.PNG"));
	JButton O_Minus = new JButton(new ImageIcon("src/빼기.PNG"));
	JButton A_Plus = new JButton(new ImageIcon("src/더하기.PNG"));
	JButton A_Minus = new JButton(new ImageIcon("src/빼기.PNG"));
	JButton C_Plus = new JButton(new ImageIcon("src/더하기.PNG"));
	JButton C_Minus = new JButton(new ImageIcon("src/빼기.PNG"));

	JButton bB1 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB2 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB3 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB4 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB5 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB6 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB7 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB8 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB9 = new JButton(new ImageIcon("src/뒤.png"));
	JButton bB10 = new JButton(new ImageIcon("src/뒤.png"));

	JButton SB1 = new JButton(SeatEmpty);
	JButton SB2 = new JButton(SeatEmpty);
	JButton SB3 = new JButton(SeatEmpty);
	JButton SB4 = new JButton(SeatEmpty);
	JButton SB5 = new JButton(SeatEmpty);
	JButton SB6 = new JButton(SeatEmpty);
	JButton SB7 = new JButton(SeatEmpty);
	JButton SB8 = new JButton(SeatEmpty);
	JButton SB9 = new JButton(SeatEmpty);
	JButton SB10 = new JButton(SeatEmpty);
	JButton SB11 = new JButton(SeatEmpty);
	JButton SB12 = new JButton(SeatEmpty);
	JButton SB13 = new JButton(SeatEmpty);
	JButton SB14 = new JButton(SeatEmpty);
	JButton SB15 = new JButton(SeatEmpty);
	JButton SB16 = new JButton(SeatEmpty);

	private JButton [][] tempButton = new JButton[7][7];


	JList S_AreaList = new JList();
	JList S_StationList = new JList();
	JList L_AreaList = new JList();
	JList L_StationList = new JList();
	JList S_TimeList = new JList();
	JList TicketList = new JList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Project2 p = new Project2();
		p.go();
		p.dbConnectionInit();

	}

	private void dbConnectionInit(){
		try {
			Class.forName("com.mysql.jdbc.Driver");					
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project2", "root", "dmlrhd");	

		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC 드라이버 클래스를 찾을 수 없습니다 : " + cnfe.getMessage());
		}
		catch (Exception ex) {
			System.out.println("DB 연결 에러 : " + ex.getMessage());
		}
	}

	public void go() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(year+""+month+"0"+date);

		ALLPrice = 0;
		Old = 0;
		Adult = 0;
		Child = 0;
		count = 0;
		for(int x=0;x<10;x++)
			SeatCheck[x]=false;

		size.setSize(480,1100);


		MainPanel = new MainPanel();
		MainPanel.setBounds(0,0,480,720);

		loginPanel = new LoginPanel();
		loginPanel.setBounds(0,0,480,720);

		newPanel = new NewPanel();
		newPanel.setBounds(0,0,480,720);

		reservationPanel = new ReservationPanel(); 
		reservationPanel.setBounds(0,0,480,720);

		usefulPanel = new UsefulPanel(); 
		usefulPanel.setBounds(0,0,480,720);

		centerPanel = new CenterPanel(); 
		centerPanel.setBounds(0,0,480,720);

		terminalPanel = new TerminalPanel(); 
		terminalPanel.setBounds(0,0,480,720);

		companyPanel = new CompanyPanel(); 
		companyPanel.setBounds(0,0,480,720);

		myPagePanel = new MyPagePanel(); 
		myPagePanel.setBounds(0,0,480,720);

		ticketCheckPanel = new TicketCheckPanel(); 
		ticketCheckPanel.setBounds(0,0,480,720);

		ticketCheckPanel2 = new TicketCheckPanel2(); 
		ticketCheckPanel2.setBounds(0,0,480,720);
		
		s_StationSelectPanel = new S_StationSelectPanel(); 
		s_StationSelectPanel.setBounds(500,200,480,320);

		l_StationSelectPanel = new L_StationSelectPanel(); 
		l_StationSelectPanel.setBounds(500,200,480,320);

		inquiryPanel = new InquiryPanel(); 
		inquiryPanel.setBounds(0,0,480,720);

		seatPanel = new SeatPanel(); 
		seatPanel.setBounds(0,0,480,1100);
		seatPanel.setPreferredSize(size);

		memberPanel = new MemberPanel(); 
		memberPanel.setBounds(0,0,480,720);

		JPanel leftTopPanel = new JPanel(new RiverLayout());

		cScroller = new JScrollPane(S_AreaList);
		cScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		S_AreaList.setVisibleRowCount(6);
		S_AreaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		S_AreaList.setFixedCellWidth(100);

		leftTopPanel.add("br center", new JLabel("지역"));
		leftTopPanel.add("p center", cScroller);

		JPanel rightTopPanel = new JPanel(new RiverLayout());

		cScroller1 = new JScrollPane(S_StationList);
		cScroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		S_StationList.setVisibleRowCount(6);
		S_StationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		S_StationList.setFixedCellWidth(100);



		rightTopPanel.add("br center", new JLabel("터미널"));
		rightTopPanel.add("p center", cScroller1);

		JPanel topPanel = new JPanel(new GridLayout(1,2));
		topPanel.add(leftTopPanel);
		topPanel.add(rightTopPanel);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add("center", S_OK);

		JPanel startPanel = new JPanel();
		startPanel.add(topPanel, BorderLayout.CENTER);
		startPanel.add(bottomPanel, BorderLayout.SOUTH);

		JPanel leftTopPanel1 = new JPanel(new RiverLayout());

		cScroller2 = new JScrollPane(L_AreaList);
		cScroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		L_AreaList.setVisibleRowCount(6);
		L_AreaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		L_AreaList.setFixedCellWidth(100);

		leftTopPanel1.add("br center", new JLabel("지역"));
		leftTopPanel1.add("p center", cScroller2);

		JPanel rightTopPanel1 = new JPanel(new RiverLayout());

		cScroller3 = new JScrollPane(L_StationList);
		cScroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		L_StationList.setVisibleRowCount(6);
		L_StationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		L_StationList.setFixedCellWidth(100);

		rightTopPanel1.add("br center", new JLabel("터미널"));
		rightTopPanel1.add("p center", cScroller3);

		JPanel topPanel1 = new JPanel(new GridLayout(1,2));
		topPanel1.add(leftTopPanel1);
		topPanel1.add(rightTopPanel1);

		JPanel bottomPanel1 = new JPanel();
		bottomPanel1.add("center", L_OK);

		JPanel startPanel1 = new JPanel();
		startPanel1.add(topPanel1, BorderLayout.CENTER);
		startPanel1.add(bottomPanel1, BorderLayout.SOUTH);

		cScroller4 = new JScrollPane(S_TimeList);
		cScroller4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		S_TimeList.setVisibleRowCount(6);
		S_TimeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		S_TimeList.setFixedCellWidth(100);


		seatPanel.setLayout(null);
		cScroller5 = new JScrollPane(seatPanel);
		cScroller5.setBounds(0,0,480,716);
		cScroller5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		cScroller6 = new JScrollPane(TicketList);
		cScroller6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cScroller6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		TicketList.setVisibleRowCount(6);
		TicketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TicketList.setFixedCellWidth(150);


		lp.add(loginPanel,new Integer(2));
		lp.add(MainPanel,new Integer(1));

		lp1.add(usefulPanel, new Integer(4));
		lp1.add(centerPanel, new Integer(3));
		lp1.add(terminalPanel, new Integer(2));
		lp1.add(companyPanel, new Integer(1));

		lp2.add(reservationPanel, new Integer(4));
		lp2.add(inquiryPanel, new Integer(3));
		lp2.add(cScroller5, new Integer(2));
		lp2.add(memberPanel, new Integer(1));

		ID = new JTextField(20);
		password = new JPasswordField(20);
		phone = new JTextField(20);
		birthday = new JTextField(20);

		NewID = new JTextField(20);
		NewPassword = new JTextField(20);
		NewName = new JTextField(20);
		NewPhone = new JTextField(20);
		NewBirthday = new JTextField(20);

		M_name = new JTextField(20);
		M_ID = new JTextField(20);
		M_Password = new JTextField(20);
		M_Phone = new JTextField(20);
		M_Number = new JTextField(20);
		M_Birthday = new JTextField(20);

		S_Station = new JTextField(50);
		L_Station = new JTextField(50);
		Date = new JTextField(50);

		S_Time = new JTextField(50);
		L_Time = new JTextField(50);
		BusNumber = new JTextField(50);

		ALL_num = new JTextField("0");
		O_num = new JTextField("0");
		O_num.setHorizontalAlignment(JTextField.CENTER);
		A_num = new JTextField("0");
		A_num.setHorizontalAlignment(JTextField.CENTER);
		C_num = new JTextField("0");
		C_num.setHorizontalAlignment(JTextField.CENTER);
		Price = new JTextField("0");
		
		T_id = new JTextField(10);

		mainButton1.addActionListener(new MainButtonListener());
		mainButton2.addActionListener(new MainButtonListener());
		mainButton3.addActionListener(new MainButtonListener());
		mainButton4.addActionListener(new MainButtonListener());
		mainButton5.addActionListener(new MainButtonListener());
		mainButton6.addActionListener(new MainButtonListener());
		mainButton7.addActionListener(new MainButtonListener());

		login.addActionListener(new LoginButtonListener());
		newButton.addActionListener(new LoginButtonListener());
		OK.addActionListener(new OKButtonListener());
		Exit.addActionListener(new ExitButtonListener());
		Save.addActionListener(new SaveButtonListener());
		S_OK.addActionListener(new OKButtonListener());
		Seat_OK.addActionListener(new OKButtonListener());
		L_OK.addActionListener(new OKButtonListener());
		inquiry.addActionListener(new OKButtonListener());
		SeatButton.addActionListener(new OKButtonListener());

		O_Plus.addActionListener(new P_MButtonListener());
		O_Minus.addActionListener(new P_MButtonListener());
		A_Plus.addActionListener(new P_MButtonListener());
		A_Minus.addActionListener(new P_MButtonListener());
		C_Plus.addActionListener(new P_MButtonListener());
		C_Minus.addActionListener(new P_MButtonListener());

		firstButton.addActionListener(new MainButtonListener());
		lastButton.addActionListener(new MainButtonListener());
		startDateButton.addActionListener(new MainButtonListener());
		gradeButton.addActionListener(new MainButtonListener());
		logoutButton.addActionListener(new MainButtonListener());
		FinishReservation.addActionListener(new MainButtonListener());

		centerButton.addActionListener(new UsefulButtonListener());
		terminalButton.addActionListener(new UsefulButtonListener());
		companyButton.addActionListener(new UsefulButtonListener());

		CalButton.addActionListener(new MainButtonListener());

		bB1.addActionListener(new BackButtonListener());
		bB2.addActionListener(new BackButtonListener());
		bB3.addActionListener(new BackButtonListener());
		bB4.addActionListener(new BackButtonListener());
		bB5.addActionListener(new BackButtonListener());
		bB6.addActionListener(new BackButtonListener());
		bB7.addActionListener(new BackButtonListener());
		bB8.addActionListener(new BackButtonListener());
		bB9.addActionListener(new BackButtonListener());
		bB10.addActionListener(new BackButtonListener());

		SB1.addActionListener(new SeatSelectButtonListener());
		SB2.addActionListener(new SeatSelectButtonListener());
		SB3.addActionListener(new SeatSelectButtonListener());
		SB4.addActionListener(new SeatSelectButtonListener());
		SB5.addActionListener(new SeatSelectButtonListener());
		SB6.addActionListener(new SeatSelectButtonListener());
		SB7.addActionListener(new SeatSelectButtonListener());
		SB8.addActionListener(new SeatSelectButtonListener());
		SB9.addActionListener(new SeatSelectButtonListener());
		SB10.addActionListener(new SeatSelectButtonListener());
		SB11.addActionListener(new SeatSelectButtonListener());
		SB12.addActionListener(new SeatSelectButtonListener());
		SB13.addActionListener(new SeatSelectButtonListener());
		SB14.addActionListener(new SeatSelectButtonListener());
		SB15.addActionListener(new SeatSelectButtonListener());
		SB16.addActionListener(new SeatSelectButtonListener());


		S_AreaList.addListSelectionListener(new S_AreaListListener());
		S_StationList.addListSelectionListener(new S_StationListListener());
		L_AreaList.addListSelectionListener(new L_AreaListListener());
		L_StationList.addListSelectionListener(new L_StationListListener());
		S_TimeList.addListSelectionListener(new S_TimeListListener());


		mainButton1.setOpaque(false);
		mainButton1.setContentAreaFilled(false);
		mainButton1.setBorderPainted(false);

		mainButton2.setOpaque(false);
		mainButton2.setContentAreaFilled(false);
		mainButton2.setBorderPainted(false);

		mainButton3.setOpaque(false);
		mainButton3.setContentAreaFilled(false);
		mainButton3.setBorderPainted(false);

		mainButton4.setOpaque(false);
		mainButton4.setContentAreaFilled(false);
		mainButton4.setBorderPainted(false);

		mainButton5.setOpaque(false);
		mainButton5.setContentAreaFilled(false);
		mainButton5.setBorderPainted(false);

		mainButton6.setOpaque(false);
		mainButton6.setContentAreaFilled(false);
		mainButton6.setBorderPainted(false);

		mainButton7.setOpaque(false);
		mainButton7.setContentAreaFilled(false);
		mainButton7.setBorderPainted(false);

		firstButton.setOpaque(false);
		firstButton.setContentAreaFilled(false);
		firstButton.setBorderPainted(false);


		lastButton.setOpaque(false);
		lastButton.setContentAreaFilled(false);
		lastButton.setBorderPainted(false);

		startDateButton.setOpaque(false);
		startDateButton.setContentAreaFilled(false);
		startDateButton.setBorderPainted(false);

		gradeButton.setOpaque(false);
		gradeButton.setContentAreaFilled(false);
		gradeButton.setBorderPainted(false);

		inquiry.setOpaque(false);
		inquiry.setContentAreaFilled(false);
		inquiry.setBorderPainted(false);

		SeatButton.setOpaque(false);
		SeatButton.setContentAreaFilled(false);
		SeatButton.setBorderPainted(false);
		SeatButton.setFont(new Font("HY견명조",Font.BOLD,20));

		centerButton.setOpaque(false);
		centerButton.setContentAreaFilled(false);
		centerButton.setBorderPainted(false);

		terminalButton.setOpaque(false);
		terminalButton.setContentAreaFilled(false);
		terminalButton.setBorderPainted(false);

		companyButton.setOpaque(false);
		companyButton.setContentAreaFilled(false);
		companyButton.setBorderPainted(false);

		bB1.setOpaque(false);
		bB1.setContentAreaFilled(false);
		bB1.setBorderPainted(false);

		bB2.setOpaque(false);
		bB2.setContentAreaFilled(false);
		bB2.setBorderPainted(false);

		bB3.setOpaque(false);
		bB3.setContentAreaFilled(false);
		bB3.setBorderPainted(false);

		bB4.setOpaque(false);
		bB4.setContentAreaFilled(false);
		bB4.setBorderPainted(false);

		bB5.setOpaque(false);
		bB5.setContentAreaFilled(false);
		bB5.setBorderPainted(false);

		bB6.setOpaque(false);
		bB6.setContentAreaFilled(false);
		bB6.setBorderPainted(false);

		bB7.setOpaque(false);
		bB7.setContentAreaFilled(false);
		bB7.setBorderPainted(false);

		bB8.setOpaque(false);
		bB8.setContentAreaFilled(false);
		bB8.setBorderPainted(false);

		bB9.setOpaque(false);
		bB9.setContentAreaFilled(false);
		bB9.setBorderPainted(false);
		
		bB10.setOpaque(false);
		bB10.setContentAreaFilled(false);
		bB10.setBorderPainted(false);

		SB1.setOpaque(false);
		SB1.setContentAreaFilled(false);
		SB1.setBorderPainted(false);

		SB2.setOpaque(false);
		SB2.setContentAreaFilled(false);
		SB2.setBorderPainted(false);

		SB3.setOpaque(false);
		SB3.setContentAreaFilled(false);
		SB3.setBorderPainted(false);

		SB4.setOpaque(false);
		SB4.setContentAreaFilled(false);
		SB4.setBorderPainted(false);

		SB5.setOpaque(false);
		SB5.setContentAreaFilled(false);
		SB5.setBorderPainted(false);

		SB6.setOpaque(false);
		SB6.setContentAreaFilled(false);
		SB6.setBorderPainted(false);

		SB7.setOpaque(false);
		SB7.setContentAreaFilled(false);
		SB7.setBorderPainted(false);

		SB8.setOpaque(false);
		SB8.setContentAreaFilled(false);
		SB8.setBorderPainted(false);

		SB9.setOpaque(false);
		SB9.setContentAreaFilled(false);
		SB9.setBorderPainted(false);

		SB10.setOpaque(false);
		SB10.setContentAreaFilled(false);
		SB10.setBorderPainted(false);

		SB11.setOpaque(false);
		SB11.setContentAreaFilled(false);
		SB11.setBorderPainted(false);

		SB12.setOpaque(false);
		SB12.setContentAreaFilled(false);
		SB12.setBorderPainted(false);

		SB13.setOpaque(false);
		SB13.setContentAreaFilled(false);
		SB13.setBorderPainted(false);

		SB14.setOpaque(false);
		SB14.setContentAreaFilled(false);
		SB14.setBorderPainted(false);

		SB15.setOpaque(false);
		SB15.setContentAreaFilled(false);
		SB15.setBorderPainted(false);

		SB16.setOpaque(false);
		SB16.setContentAreaFilled(false);
		SB16.setBorderPainted(false);

		O_Plus.setOpaque(false);
		O_Plus.setContentAreaFilled(false);
		O_Plus.setBorderPainted(false);

		O_Minus.setOpaque(false);
		O_Minus.setContentAreaFilled(false);
		O_Minus.setBorderPainted(false);

		A_Plus.setOpaque(false);
		A_Plus.setContentAreaFilled(false);
		A_Plus.setBorderPainted(false);

		A_Minus.setOpaque(false);
		A_Minus.setContentAreaFilled(false);
		A_Minus.setBorderPainted(false);

		C_Plus.setOpaque(false);
		C_Plus.setContentAreaFilled(false);
		C_Plus.setBorderPainted(false);

		C_Minus.setOpaque(false);
		C_Minus.setContentAreaFilled(false);
		C_Minus.setBorderPainted(false);

		Exit.setOpaque(false);
		Exit.setContentAreaFilled(false);
		Exit.setBorderPainted(false);

		Save.setOpaque(false);
		Save.setContentAreaFilled(false);
		Save.setBorderPainted(false);

		logoutButton.setOpaque(false);
		logoutButton.setContentAreaFilled(false);
		logoutButton.setBorderPainted(false);

		CalButton.setOpaque(false);
		CalButton.setContentAreaFilled(false);
		CalButton.setBorderPainted(false);

		FinishReservation.setOpaque(false);
		FinishReservation.setContentAreaFilled(false);
		FinishReservation.setBorderPainted(false);

		frame.add(lp);
		frame.setSize(480,750);
		frame.setVisible(true);

		NewFrame.add(newPanel);
		NewFrame.setSize(480,550);
		NewFrame.setVisible(false);

		ReservationFrame.add(lp2);
		ReservationFrame.setSize(493, 750);
		ReservationFrame.setVisible(false);

		UsefulFrame.add(lp1);
		UsefulFrame.setSize(480, 750);
		UsefulFrame.setVisible(false);

		MyPageFrame.add(myPagePanel);
		MyPageFrame.setSize(480, 750);
		MyPageFrame.setVisible(false);

		TicketCheckFrame.add(ticketCheckPanel);
		TicketCheckFrame.setSize(480, 750);
		TicketCheckFrame.setVisible(false);
		
		TicketCheckFrame2.add(ticketCheckPanel2);
		TicketCheckFrame2.setSize(480, 750);
		TicketCheckFrame2.setVisible(false);

		S_StationSelectFrame.add(s_StationSelectPanel);
		S_StationSelectFrame.setBounds(500,200,480, 350);
		S_StationSelectFrame.setVisible(false);

		L_StationSelectFrame.add(l_StationSelectPanel);
		L_StationSelectFrame.setBounds(500,200,480, 350);
		L_StationSelectFrame.setVisible(false);


		DateFrame.setLayout(gridLayout);
		DateFrame.setSize(500, 300);
		DateFrame.setVisible(false);


		login.setFont(new Font("Cambria",Font.BOLD,24));
		login.setOpaque(false);
		login.setContentAreaFilled(false);
		login.setBorderPainted(false);

		newButton.setFont(new Font("Cambria",Font.BOLD,24));
		newButton.setOpaque(false);
		newButton.setContentAreaFilled(false);
		newButton.setBorderPainted(false);

		OK.setFont(new Font("Cambria",Font.BOLD,24));
		OK.setOpaque(false);
		OK.setContentAreaFilled(false);
		OK.setBorderPainted(false);

		Seat_OK.setFont(new Font("Cambria",Font.BOLD,24));
		Seat_OK.setOpaque(false);
		Seat_OK.setContentAreaFilled(false);
		Seat_OK.setBorderPainted(false);

		MainPanel.add(mainButton1);
		MainPanel.add(mainButton2);
		MainPanel.add(mainButton3);
		MainPanel.add(mainButton4);
		MainPanel.add(mainButton5);
		MainPanel.add(mainButton6);
		MainPanel.add(mainButton7);

		loginPanel.add(ID);
		loginPanel.add(password);
		loginPanel.add(login);
		loginPanel.add(newButton);
		loginPanel.add(phone);
		loginPanel.add(birthday);

		newPanel.add(NewID);
		newPanel.add(NewPassword);
		newPanel.add(NewName);
		newPanel.add(NewPhone);
		newPanel.add(NewBirthday);
		newPanel.add(OK);

		reservationPanel.add(firstButton);
		reservationPanel.add(lastButton);
		reservationPanel.add(startDateButton);
		reservationPanel.add(gradeButton);
		reservationPanel.add(inquiry);
		reservationPanel.add(bB6);

		s_StationSelectPanel.add(startPanel);
		l_StationSelectPanel.add(startPanel1);

		usefulPanel.add(centerButton);
		usefulPanel.add(terminalButton);
		usefulPanel.add(companyButton);
		usefulPanel.add(bB4);


		centerPanel.add(bB1);
		terminalPanel.add(bB2);
		companyPanel.add(bB3);


		myPagePanel.add(M_Number);
		myPagePanel.add(M_ID);
		myPagePanel.add(M_Password);
		myPagePanel.add(M_Birthday);
		myPagePanel.add(M_Phone);
		myPagePanel.add(M_name);
		myPagePanel.add(Exit);
		myPagePanel.add(Save);
		myPagePanel.add(logoutButton);

		ticketCheckPanel.add(bB5);
		
		ticketCheckPanel2.add(bB10);
		ticketCheckPanel2.add(cScroller6);
		
		inquiryPanel.add(CalButton);
		inquiryPanel.add(bB7);
		inquiryPanel.add(cScroller4);
		inquiryPanel.add(SeatButton);

		seatPanel.add(bB8);
		seatPanel.add(Seat_OK);
		seatPanel.add(SB1);
		seatPanel.add(SB2);
		seatPanel.add(SB3);
		seatPanel.add(SB4);
		seatPanel.add(SB5);
		seatPanel.add(SB6);
		seatPanel.add(SB7);
		seatPanel.add(SB8);
		seatPanel.add(SB9);
		seatPanel.add(SB10);
		seatPanel.add(SB11);
		seatPanel.add(SB12);
		seatPanel.add(SB13);
		seatPanel.add(SB14);
		seatPanel.add(SB15);
		seatPanel.add(SB16);

		memberPanel.add(bB9);
		memberPanel.add(O_Plus);
		memberPanel.add(O_Minus);
		memberPanel.add(A_Plus);
		memberPanel.add(A_Minus);
		memberPanel.add(C_Plus);
		memberPanel.add(C_Minus);
		memberPanel.add(O_num);
		memberPanel.add(A_num);
		memberPanel.add(C_num);
		memberPanel.add(Price);
		memberPanel.add(FinishReservation);

		cal.set(Calendar.DATE,1);
		yoil=cal.get(Calendar.DAY_OF_WEEK);

		System.out.println(yoil);
		for(int i=0;i<yoil-1;i++) {
			CalArr.add(0,"");
		}
		for(int i=1;i<lastDay+1;i++) {
			CalArr.add(""+i+"");
		}


		for(int i =0 ; i<CalArr.size();i++) {
			tempButton[i/7][i%7] = new JButton(CalArr.get(i)+"");
			if(i<date+yoil-1 )
				tempButton[i/7][i%7].setEnabled(false);;
				if(i%7==0)
					tempButton[i/7][i%7].setForeground(Color.RED);
				else if(i%7==6)
					tempButton[i/7][i%7].setForeground(Color.BLUE);
				DateFrame.add(tempButton[i/7][i%7]);
				tempButton[i/7][i%7].addActionListener(new DateButton());
		}

	}
	class MainPanel extends JPanel{
		public void paintComponent(Graphics g) {
			mainBackground.draw(g);
			main1.draw(g);
			main2.draw(g);
			main3.draw(g);
			main4.draw(g);
			main5.draw(g);
			main6.draw(g);
			main7.draw(g);

			mainButton1.setBounds(0, 0, 480, 180);
			mainButton2.setBounds(0, 180, 480, 180);
			mainButton3.setBounds(0, 360, 480, 180);
			mainButton4.setBounds(0, 540, 120, 160);
			mainButton5.setBounds(120, 540, 120, 160);
			mainButton6.setBounds(240, 540, 120, 160);
			mainButton7.setBounds(360, 540, 120, 160);


		}
	}
	class NewPanel extends JPanel{
		public void paintComponent(Graphics g) {
			loginBackground.draw(g);

			g.setColor(Color.BLACK);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("회원가입",130,95);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("ID",120,160);
			NewID.setFont(new Font("Cambria",Font.BOLD,24));
			NewID.setBounds(157,125,180,45);


			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("PassWord",43,210);
			NewPassword.setFont(new Font("Cambria",Font.BOLD,24));
			NewPassword.setBounds(157,178,180,45);


			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("Name",90,260);
			NewName.setFont(new Font("HY견명조",Font.BOLD,24));
			NewName.setBounds(157,231,130,45);


			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("Phone",80,315);
			NewPhone.setFont(new Font("Cambria",Font.BOLD,24));
			NewPhone.setBounds(157,284,180,45);


			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("Birthday",55,365);
			NewBirthday.setFont(new Font("Cambria",Font.BOLD,24));
			NewBirthday.setBounds(157,337,160,45);

			OK.setFont(new Font("HY견명조",Font.BOLD,20));
			OK.setBounds(300,400,150,50);
		}
	}
	class LoginPanel extends JPanel{
		public void paintComponent(Graphics g) {
			loginBackground.draw(g);

			g.setColor(Color.BLACK);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("로그인",130,95);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("ID",120,160);
			ID.setFont(new Font("Cambria",Font.BOLD,24));
			ID.setBounds(157,125,180,45);

			g.drawString("PassWord",43,210);
			password.setFont(new Font("Cambria",Font.BOLD,24));
			password.setBounds(157,178,180,45);

			login.setBounds(262,230,110,50);
			newButton.setBounds(342, 230, 110, 50);

			g.setColor(Color.BLACK);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("비회원 로그인",50,345);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Cambria",Font.BOLD,24));
			g.drawString("Phone",80,410);
			phone.setFont(new Font("Cambria",Font.BOLD,24));
			phone.setBounds(157,375,180,45);

			g.drawString("Birthday",55,460);
			birthday.setFont(new Font("Cambria",Font.BOLD,24));
			birthday.setBounds(157,428,180,45);

		}
	}
	class S_StationSelectPanel extends JPanel{
		public void paintComponent(Graphics g) {

			try {
				Statement stmt = conn.createStatement();			// SQL 문을 작성을 위한  Statement 객체 생성

				// 현재 DB에 있는 내용 추출해서 강아지 목록을 names 리스트에 출력하기
				ResultSet rs = stmt.executeQuery("SELECT * FROM Area_info");
				Vector<String> list = new Vector<String>();
				while (rs.next()) {
					list.add(rs.getString("Area"));		
				}
				stmt.close();					
				S_AreaList.setListData(list);

				if (!list.isEmpty())							
					S_AreaList.setSelectedIndex(0);
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			}
		}
	}
	class L_StationSelectPanel extends JPanel{
		public void paintComponent(Graphics g) {

			try {
				Statement stmt = conn.createStatement();			// SQL 문을 작성을 위한  Statement 객체 생성

				// 현재 DB에 있는 내용 추출해서 강아지 목록을 names 리스트에 출력하기
				ResultSet rs = stmt.executeQuery("SELECT * FROM Area_info");
				Vector<String> list = new Vector<String>();
				while (rs.next()) {
					list.add(rs.getString("Area"));		
				}
				stmt.close();					
				L_AreaList.setListData(list);

				if (!list.isEmpty())							
					L_AreaList.setSelectedIndex(0);
			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			}
		}
	}

	class ReservationPanel extends JPanel {
		public void paintComponent(Graphics g) {

			mainBackground.draw(g);
			Start.draw(g);
			Last.draw(g);
			DateImage.draw(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("예매",200,35);

			firstButton.setBounds(-10, 50, 240, 155);
			lastButton.setBounds(230, 50, 240, 155);
			startDateButton.setBounds(-10, 205, 480, 110);
			gradeButton.setBounds(-10, 315, 480, 110);

			inquiry.setFont(new Font("HY견명조",Font.BOLD,20));
			inquiry.setBounds(280,420,150,50);
			bB6.setBounds(0, 0, 60, 60);


			g.setColor(Color.BLACK);
			g.setFont(new Font("HY견명조",Font.BOLD,22));
			g.drawString(S_Station.getText(),10,140);
			g.drawString(L_Station.getText(),250,140);
			g.drawString(Date.getText(), 10, 280);




		}
	}
	class InquiryPanel extends JPanel{
		public void paintComponent(Graphics g) {
			setBackground(Color.WHITE);
			mainBackground.draw(g);
			location.draw(g);


			SeatButton.setBounds(270,600,150,50);

			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("조회결과",180,35);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString(S_Station.getText(),60,90);
			g.drawString(L_Station.getText(),60,145);

			g.setFont(new Font("HY견명조",Font.BOLD,13));
			g.drawString("­요금 정보는 요금안내표를 확인해주시길 바랍니다.", 20, 185);
			g.drawString("­소요/도착시간은 참고용으로 교통상황에 따라 달라질 수 있습니다.", 20, 210);

			g.setColor(Color.BLACK);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString(Date.getText(), 165, 260);

			g.drawString("출발시간", 30, 350);
			g.setFont(new Font("HY견명조",Font.BOLD,16));
			g.drawString("버스 번호 ", 210, 400);
			g.drawString(BusNumber.getText(), 300, 400);
			g.drawString("출발 시간 ", 210, 440);
			g.drawString(S_Time.getText(), 300, 440);
			g.drawString("도착 시간 ", 210, 480);
			g.drawString(L_Time.getText(), 300, 480);

			CalButton.setBounds(340, 210, 86, 86);
			bB7.setBounds(0, 0, 60, 60);


			cScroller4.setBounds(30, 380, 100, 100);
		}
	}
	class TicketCheckPanel extends JPanel{
		public void paintComponent(Graphics g) {

			setBackground(Color.WHITE);
			TicketCheckBackground.draw(g);
			noTicket.draw(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("예매 확인 및 변경",130,35);

			bB5.setBounds(0, 0, 60, 60);
		}
	}
	class TicketCheckPanel2 extends JPanel{
		public void paintComponent(Graphics g) {
			mainBackground.draw(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("예매 확인 및 변경",130,35);
			
			cScroller6.setBounds(30, 180, 100, 150);
			bB10.setBounds(0, 0, 60, 60);
		}
	}
	class UsefulPanel extends JPanel{
		public void paintComponent(Graphics g) {
			background.draw(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("이용문의/안내",150,35);

			centerButton.setBounds(0, 50, 480, 110);
			terminalButton.setBounds(0, 160, 480, 110);
			companyButton.setBounds(0, 270, 480, 110);
			bB4.setBounds(0, 0, 60, 60);
		}
	}
	class CenterPanel extends JPanel{
		public void paintComponent(Graphics g) {
			background.draw(g);

			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("고객센터",150,35);
			g.setFont(new Font("HY견명조",Font.BOLD,20));
			g.drawString("상담가능시간 : 09:00~ 18:00",10,100);
			g.drawString("토요일, 일요일, 법정 공휴일 휴무",10,130);

			g.setFont(new Font("Cambria",Font.BOLD,30));
			g.drawString("Mobile : 1644-3070 ",10,200);

			bB1.setBounds(0, 0, 60, 60);


		}
	}
	class TerminalPanel extends JPanel{
		public void paintComponent(Graphics g) {
			background.draw(g);
			bB2.setBounds(0, 0, 60, 60);

		}
	}
	class CompanyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			background.draw(g);
			bB3.setBounds(0, 0, 60, 60);

		}
	}
	class MyPagePanel extends JPanel{
		public void paintComponent(Graphics g) {
			loginBackground.draw(g);

			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,30));
			g.drawString("마이페이지",150,55);

			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("고객번호",50,155);
			M_Number.setFont(new Font("HY견명조",Font.BOLD,24));
			M_Number.setBounds(160,120,50,50);


			g.drawString("고객이름",50,215);
			M_name.setFont(new Font("HY견명조",Font.BOLD,24));
			M_name.setBounds(160,180,100,50);

			g.drawString("고객ID",70,275);
			M_ID.setFont(new Font("HY견명조",Font.BOLD,24));
			M_ID.setBounds(160,240,180,50);

			g.drawString("고객비밀번호",0,335);
			M_Password.setFont(new Font("HY견명조",Font.BOLD,24));
			M_Password.setBounds(160,300,180,50);

			g.drawString("생년월일",50,395);
			M_Birthday.setFont(new Font("HY견명조",Font.BOLD,24));
			M_Birthday.setBounds(160,360,180,50);

			g.drawString("전화번호",50,455);
			M_Phone.setFont(new Font("HY견명조",Font.BOLD,24));
			M_Phone.setBounds(160,420,250,50);

			Exit.setBounds(360,500,100,50);
			Exit.setFont(new Font("Cambria",Font.BOLD,24));

			Save.setBounds(280,500,100,50);
			Save.setFont(new Font("Cambria",Font.BOLD,24));

			logoutButton.setBounds(350,15,90,60);

		}
	}
	class MemberPanel extends JPanel{
		public void paintComponent(Graphics g) {
			memberBackground.draw(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("승차 인원",170,45);
			g.setColor(Color.black);
			g.setFont(new Font("HY견명조",Font.BOLD,20));
			g.drawString("승차 인원수를 선택하세요.",30,100);
			g.setFont(new Font("HY견명조",Font.BOLD,24));
			g.drawString("총 인원",30,210);

			g.drawString(ALL_num.getText(), 140, 210);
			g.drawString("명",180,210);
			g.drawString("노인",60,270);
			g.drawString("(만 65세 이상)",17,300);
			g.drawString("성인",60,370);
			g.drawString("(만 19세 이상)",17,400);
			g.drawString("미성년자",60,470);
			g.drawString("(만 19세 미만)",17,500);

			O_num.setFont(new Font("HY견명조",Font.BOLD,24));
			O_num.setBounds(280,250,102,50);
			A_num.setFont(new Font("HY견명조",Font.BOLD,24));
			A_num.setBounds(280,350,102,50);
			C_num.setFont(new Font("HY견명조",Font.BOLD,24));
			C_num.setBounds(280,450,102,50);

			g.drawString("총 가격", 230, 582);
			Price.setFont(new Font("HY견명조",Font.BOLD,24));
			Price.setBounds(330,550,120,50);

			O_Plus.setBounds(380,250,50,50);
			O_Minus.setBounds(230,250,50,50);
			A_Plus.setBounds(380,350,50,50);
			A_Minus.setBounds(230,350,50,50);
			C_Plus.setBounds(380,450,50,50);
			C_Minus.setBounds(230,450,50,50);
			bB9.setBounds(0, 0, 60, 60);
			FinishReservation.setFont(new Font("HY견명조",Font.BOLD,24));
			FinishReservation.setBounds(305, 650, 140, 50);
		}
	}
	class SeatPanel extends JPanel{
		public void paintComponent(Graphics g) {
			SeatBackground.draw(g);
			SB1.setBounds(10, 120, 92, 105);
			SB2.setBounds(110, 120, 92, 105);
			SB3.setBounds(370, 120, 92, 105);
			SB4.setBounds(10, 235, 92, 105);
			SB5.setBounds(110, 235, 92, 105);
			SB6.setBounds(370, 235, 92, 105);
			SB7.setBounds(10, 350, 92, 105);
			SB8.setBounds(110, 350, 92, 105);
			SB9.setBounds(370, 350, 92, 105);
			SB10.setBounds(10, 465, 92, 105);
			SB11.setBounds(110, 465, 92, 105);
			SB12.setBounds(370, 465, 92, 105);
			SB13.setBounds(10, 580, 92, 105);
			SB14.setBounds(110, 580, 92, 105);
			SB15.setBounds(270, 580, 92, 105);
			SB16.setBounds(370, 580, 92, 105);

			Seat_OK.setBounds(350, 950, 120, 50);
			bB8.setBounds(0, 0, 60, 60);
		}
	}
	class DateButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<8+yoil;i++) {
				if (e.getSource() == tempButton[i/7][i%7]) {
					DateCheck= true;
					Date.setText(""+year+month+"0"+tempButton[i/7][i%7].getText()+"");
					DateFrame.dispose();
				}
			}
			for(int i=8+yoil;i<CalArr.size()+1;i++) {
				if (e.getSource() == tempButton[i/7][i%7]) {
					DateCheck= true;
					Date.setText(""+year+month+""+tempButton[i/7][i%7].getText()+"");
					DateFrame.dispose();
				}
			}

			System.out.println(Date.getText());
			inquiryPanel.repaint();
		}

	}
	class SeatSelectButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button == SB1 &&count<5 &&SeatCheck[0]==false) {
				SeatData[count]=1;
				count++;
				SB1.setIcon(SeatSelect);
				SeatCheck[0]=true;
			}
			else if(button == SB1 && SeatCheck[0]==true) {
				count--;
				SB1.setIcon(SeatEmpty);
				SeatCheck[0]=false;
			}
			if(button == SB2 &&count<5 &&SeatCheck[1]==false) {
				SeatData[count]=2;
				count++;
				SB2.setIcon(SeatSelect);
				SeatCheck[1]=true;
			}
			else if(button == SB2 && SeatCheck[1]==true) {
				count--;
				SB2.setIcon(SeatEmpty);
				SeatCheck[1]=false;
			}
			if(button == SB3 &&count<5 &&SeatCheck[2]==false) {
				SeatData[count]=3;
				count++;
				SB3.setIcon(SeatSelect);
				SeatCheck[2]=true;
			}
			else if(button == SB3 && SeatCheck[2]==true) {
				count--;
				SB3.setIcon(SeatEmpty);
				SeatCheck[2]=false;
			}
			if(button == SB4 &&count<5 &&SeatCheck[3]==false) {
				SeatData[count]=4;
				count++;
				SB4.setIcon(SeatSelect);
				SeatCheck[3]=true;
			}
			else if(button == SB4 && SeatCheck[3]==true) {
				count--;
				SB4.setIcon(SeatEmpty);
				SeatCheck[3]=false;
			}
			if(button == SB5 &&count<5 &&SeatCheck[4]==false) {
				SeatData[count]=5;
				count++;
				SB5.setIcon(SeatSelect);
				SeatCheck[4]=true;
			}
			else if(button == SB5 && SeatCheck[4]==true) {
				count--;
				SB5.setIcon(SeatEmpty);
				SeatCheck[4]=false;
			}
			if(button == SB6 &&count<5 &&SeatCheck[5]==false) {
				SeatData[count]=6;
				count++;
				SB6.setIcon(SeatSelect);
				SeatCheck[5]=true;
			}
			else if(button == SB6 && SeatCheck[5]==true) {
				count--;
				SB6.setIcon(SeatEmpty);
				SeatCheck[5]=false;
			}
			if(button == SB7 &&count<5 &&SeatCheck[6]==false) {
				SeatData[count]=7;
				count++;
				SB7.setIcon(SeatSelect);
				SeatCheck[6]=true;
			}
			else if(button == SB7 && SeatCheck[6]==true) {
				count--;
				SB7.setIcon(SeatEmpty);
				SeatCheck[6]=false;
			}
			if(button == SB8 &&count<5 &&SeatCheck[7]==false) {
				SeatData[count]=8;
				count++;
				SB8.setIcon(SeatSelect);
				SeatCheck[7]=true;
			}
			else if(button == SB8 && SeatCheck[7]==true) {
				count--;
				SB8.setIcon(SeatEmpty);
				SeatCheck[7]=false;
			}
			if(button == SB9&&count<5 &&SeatCheck[8]==false) {
				SeatData[count]=9;
				count++;
				SB9.setIcon(SeatSelect);
				SeatCheck[8]=true;
			}
			else if(button == SB9 && SeatCheck[8]==true) {
				count--;
				SB9.setIcon(SeatEmpty);
				SeatCheck[8]=false;
			}
			if(button == SB10&&count<5 &&SeatCheck[9]==false) {
				SeatData[count]=10;
				count++;
				SB10.setIcon(SeatSelect);
				SeatCheck[9]=true;
			}
			else if(button == SB10 && SeatCheck[9]==true) {
				count--;
				SB10.setIcon(SeatEmpty);
				SeatCheck[9]=false;
			}
			if(button == SB11&&count<5 &&SeatCheck[10]==false) {
				SeatData[count]=11;
				count++;
				SB11.setIcon(SeatSelect);
				SeatCheck[10]=true;
			}
			else if(button == SB11 && SeatCheck[10]==true) {
				count--;
				SB11.setIcon(SeatEmpty);
				SeatCheck[10]=false;
			}
			if(button == SB12&&count<5 &&SeatCheck[11]==false) {
				SeatData[count]=12;
				count++;
				SB12.setIcon(SeatSelect);
				SeatCheck[11]=true;
			}
			else if(button == SB12 && SeatCheck[11]==true) {
				count--;
				SB12.setIcon(SeatEmpty);
				SeatCheck[11]=false;
			}
			if(button == SB13&&count<5 &&SeatCheck[12]==false) {
				SeatData[count]=13;
				count++;
				SB13.setIcon(SeatSelect);
				SeatCheck[12]=true;
			}
			else if(button == SB13 && SeatCheck[12]==true) {
				count--;
				SB13.setIcon(SeatEmpty);
				SeatCheck[12]=false;
			}
			if(button == SB14&&count<5 &&SeatCheck[13]==false) {
				SeatData[count]=14;
				count++;
				SB14.setIcon(SeatSelect);
				SeatCheck[13]=true;
			}
			else if(button == SB14 && SeatCheck[13]==true) {
				count--;
				SB14.setIcon(SeatEmpty);
				SeatCheck[13]=false;
			}
			if(button == SB15&&count<5 &&SeatCheck[14]==false) {
				SeatData[count]=15;
				count++;
				SB15.setIcon(SeatSelect);
				SeatCheck[14]=true;
			}
			else if(button == SB15 && SeatCheck[14]==true) {
				count--;
				SB15.setIcon(SeatEmpty);
				SeatCheck[14]=false;
			}
			if(button == SB16&&count<5 &&SeatCheck[15]==false) {
				SeatData[count]=16;
				count++;
				SB16.setIcon(SeatSelect);
				SeatCheck[15]=true;
			}
			else if(button == SB16 && SeatCheck[15]==true) {
				count--;
				SB16.setIcon(SeatEmpty);
				SeatCheck[15]=false;
			}
		}
	}
	class LoginButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			if(button == login) {
				IDCode=ID.getText().trim();
				PasswordCode=password.getText().trim();

				String query = "Select * from my_info where ID='"+IDCode+"'";

				try {
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery(query);

					if(rs.next()){
						IDCode = rs.getString("ID");
						if(PasswordCode.equals(rs.getString("password"))){
							M_name.setText(rs.getString("Name"));			// DB에서 리턴 된 값을 가지고 택스트 박스 채움
							M_ID.setText(rs.getString("ID"));
							M_Password.setText(rs.getString("password"));
							M_Number.setText(rs.getString("M_id"));
							M_Birthday.setText(rs.getString("Birthday"));
							M_Phone.setText(rs.getString("Phone"));		
							lp.setLayer(MainPanel, 3);
						}
						else{
							JOptionPane.showMessageDialog(NewFrame, "비밀번호가 틀립니다.");
						}
					}  

					stmt.close();

				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			else if(button == newButton) {
				NewFrame.setVisible(true);
			}
		}

	}
	class OKButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button ==OK) {
				try {
					if(NewID.getText().trim().isEmpty() || NewPassword.getText().isEmpty() || NewName.getText().isEmpty() || NewPhone.getText().isEmpty()||NewBirthday.getText().isEmpty()){
						JOptionPane.showMessageDialog(NewFrame, "빈칸이 있습니다.");
					}
					else{
						Statement stmt = conn.createStatement();
						stmt.executeUpdate("INSERT INTO my_info (ID,password,name,Phone,Birthday) VALUES ('"+NewID.getText().trim()+"','"+NewPassword.getText().trim()+"',"
								+ "'"+NewName.getText().trim()+"','"+NewPhone.getText().trim()+"','"+NewBirthday.getText().trim()+"')");

						stmt.close();
						NewFrame.dispose();
					}

				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			if(button == S_OK) {
				S_StationCheck = true;
				S_StationSelectFrame.dispose();
				System.out.println(S_Station.getText());
			}
			if(button == L_OK) {
				L_StationCheck = true;
				L_StationSelectFrame.dispose();
				System.out.println(L_Station.getText());
			}
			if(button == inquiry&&S_StationCheck&&L_StationCheck&&DateCheck) {
				lp2.setLayer(inquiryPanel, 5);
				try {
					Statement stmt = conn.createStatement();			// SQL 문을 작성을 위한  Statement 객체 생성

					// 현재 DB에 있는 내용 추출해서 강아지 목록을 names 리스트에 출력하기
					ResultSet rs = stmt.executeQuery("SELECT * FROM BUS_info WHERE S_Station IN(SELECT S_id FROM Station_info "
							+ "WHERE Station = '" +
							S_Station.getText() + "') AND L_Station IN(SELECT S_id FROM Station_info"
							+ " WHERE Station = '"+L_Station.getText()+"')");
					Vector<String> list = new Vector<String>();
					while (rs.next()) {
						list.add(rs.getString("S_Clock"));		
					}
					stmt.close();					
					S_TimeList.setListData(list);

				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				}
			}
			if(button == SeatButton&&TimeCheck) {
				lp2.setLayer(cScroller5, 6);
				try {
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT seat FROM Ticket_info NATURAL JOIN Seat_info WHERE T_id IN("
							+ "SELECT T_id FROM Ticket_info WHERE B_id ='"+BusNumber.getText().trim()+"' AND DATE"
							+ " = '"+Date.getText().trim()+"')");
					while(rs.next()) {
						if(rs.getInt("seat")==1) {
							SB1.setEnabled(false);
						}
						else if(rs.getInt("seat")==2) {
							SB2.setEnabled(false);
						}
						else if(rs.getInt("seat")==3) {
							SB3.setEnabled(false);
						}
						else if(rs.getInt("seat")==4) {
							SB4.setEnabled(false);
						}
						else if(rs.getInt("seat")==5) {
							SB5.setEnabled(false);
						}
						else if(rs.getInt("seat")==6) {
							SB6.setEnabled(false);
						}
						else if(rs.getInt("seat")==7) {
							SB7.setEnabled(false);
						}
						else if(rs.getInt("seat")==8) {
							SB8.setEnabled(false);
						}
						else if(rs.getInt("seat")==9) {
							SB9.setEnabled(false);
						}
						else if(rs.getInt("seat")==10) {
							SB10.setEnabled(false);
						}
						else if(rs.getInt("seat")==11) {
							SB11.setEnabled(false);
						}
						else if(rs.getInt("seat")==12) {
							SB12.setEnabled(false);
						}
						else if(rs.getInt("seat")==13) {
							SB13.setEnabled(false);
						}
						else if(rs.getInt("seat")==14) {
							SB14.setEnabled(false);
						}
						else if(rs.getInt("seat")==15) {
							SB15.setEnabled(false);
						}
						else if(rs.getInt("seat")==16) {
							SB16.setEnabled(false);
						}

					}
				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			if(button == Seat_OK&&count>0) {
				lp2.setLayer(memberPanel, 7);
				ALL_num.setText(""+count);
			}
		}

	}


	class MainButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();		

			if(button == mainButton1) {
				ReservationFrame.setVisible(true);
			}

			if(button == mainButton2) {
				try {
				Vector<String> list = new Vector<String>();

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT T_id, B_id, Child, Adult, Old, DATE, Total FROM Ticket_info WHERE M_id="
							+ " '"+M_Number.getText().trim()+"'");
					if(rs.next()==false)
					TicketCheckFrame.setVisible(true);
					else {
						TicketCheckFrame2.setVisible(true);
					do {
						list.add(rs.getString("T_id"));
					}while(rs.next());
					TicketList.setListData(list);
					}

				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			if(button == mainButton3) {
				UsefulFrame.setVisible(true);
			}
			if(button == mainButton6) {
				try {
					Desktop.getDesktop().browse(new URL(HomePage).toURI());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(button == mainButton7) {
				MyPageFrame.setVisible(true);
			}
			if(button == logoutButton) {
				ID.setText("");
				password.setText("");
				lp.setLayer(loginPanel,2);
				lp.setLayer(MainPanel,1);
				MyPageFrame.dispose();
			}

			if(button == firstButton) {
				S_StationSelectFrame.setVisible(true);
			}
			if(button == lastButton) {
				L_StationSelectFrame.setVisible(true);
			}
			if(button == startDateButton || button == CalButton) {
				DateFrame.setVisible(true);
			}
			if(button == FinishReservation &&Adult+Old+Child==count) {
				try {
					int T_id;
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("INSERT INTO Ticket_info(M_id,B_id,Child,Adult,Old,DATE,Total) VALUES "
							+ "('"+M_Number.getText().trim()+"','"+BusNumber.getText().trim()+"',"
							+ "'"+Child+"','"+Adult+"','"+Old+"','"+Date.getText().trim()+"','"+ALLPrice+"')");
					ResultSet rs= stmt.executeQuery("SELECT T_id FROM Ticket_info ORDER BY T_id DESC LIMIT 1");
					rs.next();
					T_id = rs.getInt("T_id");
					for(int z = 0;z<count;z++) {
						stmt.executeUpdate("INSERT INTO Seat_info(T_id,Seat) VALUES"
								+ "('"+T_id+"','"+SeatData[z]+"')");
						System.out.println(SeatData[z]);
					}
					stmt.close();
					ReservationFrame.dispose();
					lp2.setLayer(reservationPanel, 4);
					lp2.setLayer(inquiryPanel, 3);
					lp2.setLayer(cScroller5, 2);
					lp2.setLayer(memberPanel, 1);
					S_Station.setText("");
					L_Station.setText("");
					S_Time.setText("");
					L_Time.setText("");
					BusNumber.setText("");
					Date.setText("");
					SB1.setIcon(SeatEmpty);
					SB1.setEnabled(true);
					SB2.setIcon(SeatEmpty);
					SB2.setEnabled(true);
					SB3.setIcon(SeatEmpty);
					SB3.setEnabled(true);
					SB4.setIcon(SeatEmpty);
					SB4.setEnabled(true);
					SB5.setIcon(SeatEmpty);
					SB5.setEnabled(true);
					SB6.setIcon(SeatEmpty);
					SB6.setEnabled(true);
					SB7.setIcon(SeatEmpty);
					SB7.setEnabled(true);
					SB8.setIcon(SeatEmpty);
					SB8.setEnabled(true);
					SB9.setIcon(SeatEmpty);
					SB9.setEnabled(true);
					SB10.setIcon(SeatEmpty);
					SB10.setEnabled(true);
					SB11.setIcon(SeatEmpty);
					SB11.setEnabled(true);
					SB12.setIcon(SeatEmpty);
					SB12.setEnabled(true);
					SB13.setIcon(SeatEmpty);
					SB13.setEnabled(true);
					SB14.setIcon(SeatEmpty);
					SB14.setEnabled(true);
					SB15.setIcon(SeatEmpty);
					SB15.setEnabled(true);
					SB16.setIcon(SeatEmpty);
					SB16.setEnabled(true);
					for(int z=0;z<16;z++) {
						SeatCheck[z]=false;
					}
					ALLPrice=0;
					count =0;
					Child=0;
					Old=0;
					Adult=0;
					O_num.setText("0");
					A_num.setText("0");
					C_num.setText("0");
					Price.setText("0");
					S_StationCheck=false;
					L_StationCheck=false;
					DateCheck=false;
					TimeCheck=false;
				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}

	}
	class UsefulButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			if(button == centerButton) {
				lp1.setLayer(centerPanel, 5);

			}
			if (button == terminalButton) {
				lp1.setLayer(terminalPanel,5);
			}
			if(button == companyButton) {
				lp1.setLayer(companyPanel, 5);
			}
		}

	}
	class BackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();

			if(button == bB1 ||button == bB2||button == bB3) {
				lp1.setLayer(centerPanel, 3);
				lp1.setLayer(terminalPanel, 2);
				lp1.setLayer(companyPanel, 1);
			}
			if(button ==bB4) {
				UsefulFrame.dispose();	
			}
			if(button == bB5) {
				TicketCheckFrame.dispose();
			}
			if(button == bB6) {
				ReservationFrame.dispose();
			}
			if(button == bB7) {
				lp2.setLayer(reservationPanel, 4);
				lp2.setLayer(inquiryPanel, 3);
				lp2.setLayer(cScroller5, 2);
			}
			if(button == bB8) {
				lp2.setLayer(cScroller5, 2);
				SB1.setIcon(SeatEmpty);
				SB1.setEnabled(true);
				SB2.setIcon(SeatEmpty);
				SB2.setEnabled(true);
				SB3.setIcon(SeatEmpty);
				SB3.setEnabled(true);
				SB4.setIcon(SeatEmpty);
				SB4.setEnabled(true);
				SB5.setIcon(SeatEmpty);
				SB5.setEnabled(true);
				SB6.setIcon(SeatEmpty);
				SB6.setEnabled(true);
				SB7.setIcon(SeatEmpty);
				SB7.setEnabled(true);
				SB8.setIcon(SeatEmpty);
				SB8.setEnabled(true);
				SB9.setIcon(SeatEmpty);
				SB9.setEnabled(true);
				SB10.setIcon(SeatEmpty);
				SB10.setEnabled(true);
				SB11.setIcon(SeatEmpty);
				SB11.setEnabled(true);
				SB12.setIcon(SeatEmpty);
				SB12.setEnabled(true);
				SB13.setIcon(SeatEmpty);
				SB13.setEnabled(true);
				SB14.setIcon(SeatEmpty);
				SB14.setEnabled(true);
				SB15.setIcon(SeatEmpty);
				SB15.setEnabled(true);
				SB16.setIcon(SeatEmpty);
				SB16.setEnabled(true);
				count=0;
				for(int z=0;z<16;z++) {
					SeatCheck[z]=false;
				}

			}
			if(button == bB9) {
				lp2.setLayer(memberPanel, 1);
				O_num.setText("0");
				A_num.setText("0");
				C_num.setText("0");
				Price.setText("0");
				Old=0;
				Adult=0;
				Child=0;
				ALLPrice=0;
			}
			if(button == bB10) {
				TicketCheckFrame2.dispose();
			}
		}
	}
	class P_MButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if(button == O_Plus) {
				Old = Integer.parseInt(O_num.getText());
				if(Old+Adult+Child<count) {
					Old++;
					O_num.setText(""+Old);		
				}
			}
			if(button == O_Minus) {
				Old = Integer.parseInt(O_num.getText());
				if(Old >0){
					Old--;
					O_num.setText(""+Old);
				}
				else{
					JOptionPane.showMessageDialog(ReservationFrame, "0개 이하로는 구매할수 없습니다.");
				}
			}
			if(button == A_Plus) {
				Adult = Integer.parseInt(A_num.getText());
				if(Old+Adult+Child<count) {
					Adult++;
					A_num.setText(""+Adult);	
				}
			}
			if(button == A_Minus) {
				Adult = Integer.parseInt(A_num.getText());
				if(Adult >0){
					Adult--;
					A_num.setText(""+Adult);
				}
				else{
					JOptionPane.showMessageDialog(ReservationFrame, "0개 이하로는 구매할수 없습니다.");
				}
			}
			if(button == C_Plus) {
				Child = Integer.parseInt(C_num.getText());
				if(Old+Adult+Child<count) {
					Child++;
					C_num.setText(""+Child);	
				}
			}
			if(button == C_Minus) {
				Child = Integer.parseInt(C_num.getText());
				if(Child >0){
					Child--;
					C_num.setText(""+Child);
				}
				else{
					JOptionPane.showMessageDialog(ReservationFrame, "0개 이하로는 구매할수 없습니다.");
				}
			}
			if(Old+Adult+Child==count) {
				try {
					Statement stmt = conn.createStatement();
					double A;
					double O;
					double C;
					int Normal;

					ResultSet rs1 = stmt.executeQuery("SELECT price FROM Time_Price NATURAL JOIN BUS_info WHERE B_id ='"+BusNumber.getText()+"'");
					rs1.next();Normal=rs1.getInt("price");
					rs1.close();
					ResultSet rs = stmt.executeQuery("SELECT price FROM Age_Price");
					rs.next();A=rs.getDouble("price");

					rs.next();O=rs.getDouble("price");

					rs.next();C=rs.getDouble("price");


					stmt.close();

					ALLPrice= (int)(A*Normal*Adult+O*Normal*Old+C*Normal*Child);

				}
				catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} 
				catch (Exception ex) {
					System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
				Price.setText(""+ALLPrice);
			}

		}
	}
	class ExitButtonListener implements ActionListener {               //내정보프레임 확인버튼 리스너
		public void actionPerformed(ActionEvent e) {
			MyPageFrame.dispose();

			String query = "Select * from my_info where ID='"+IDCode+"'";
			try {
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query);

				if(rs.next()){
					IDCode = rs.getString("ID");
					M_name.setText(rs.getString("Name"));			
					M_ID.setText(rs.getString("ID"));
					M_Password.setText(rs.getString("password"));
					M_Number.setText(rs.getString("M_id"));
					M_Birthday.setText(rs.getString("Phone"));	
					M_Phone.setText(rs.getString("Phone"));		
				}  

				stmt.close();

			}
			catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} 
			catch (Exception ex) {
				System.out.println("DB Handling 에러(DELETE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}

		}
	}
	public class SaveButtonListener implements ActionListener {
		String i;
		public void actionPerformed (ActionEvent e) {
			try {
				Statement stmt = conn.createStatement();// SQL 문을 작성을 위한  Statement 객체 생성

				i=M_Number.getText().trim();

				stmt.executeUpdate("DELETE FROM my_info WHERE name = '" +	// 현재 레코드 삭제하고 (name 필드는 변함이 없다고 가정)
						M_name.getText().trim() + "'");


				stmt.executeUpdate("INSERT INTO my_info (M_id,name,ID,password,Birthday,Phone) VALUES ('" +	// 새 레코드로 변경
						i + "', '" +
						M_name.getText().trim() + "', '" +
						M_ID.getText().trim() + "', '" +
						M_Password.getText().trim() + "','"+
						M_Birthday.getText().trim() + "', '" +
						M_Phone.getText().trim()  + "')");
				stmt.close();
				MyPageFrame.dispose();

			} catch (SQLException sqlex) {
				System.out.println("SQL 에러 : " + sqlex.getMessage());
				sqlex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("DB Handling 에러(SAVE 리스너) : " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	public class S_AreaListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting() && !S_AreaList.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();
					Vector<String> list1 = new Vector<String>();// SQL 문장 만들기 위한 Statement 객체

					ResultSet rs = stmt.executeQuery("SELECT Station FROM Station_info NATURAL JOIN Area_info WHERE area = '" +
							(String)S_AreaList.getSelectedValue() + "'AND Station <> '"+L_Station.getText()+"'");
					while (rs.next()) {
						list1.add(rs.getString("Station"));	

					}
					stmt.close();

					S_StationList.setListData(list1);
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		}
	}
	public class L_AreaListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting() && !L_AreaList.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();
					Vector<String> list2 = new Vector<String>();// SQL 문장 만들기 위한 Statement 객체

					ResultSet rs = stmt.executeQuery("SELECT Station FROM Station_info NATURAL JOIN Area_info WHERE area = '" +
							(String)L_AreaList.getSelectedValue() + "'AND Station <> '"+S_Station.getText()+"'");
					while (rs.next()) {
						list2.add(rs.getString("Station"));	

					}
					stmt.close();

					L_StationList.setListData(list2);
				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		}
	}
	public class S_StationListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting() && !S_StationList.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT Station FROM Station_info WHERE Station = '" +
							(String)S_StationList.getSelectedValue() + "'");
					rs.next();
					S_Station.setText(rs.getString("Station"));
					stmt.close();

				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		}
	}
	public class L_StationListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting() && !L_StationList.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT Station FROM Station_info WHERE Station = '" +
							(String)L_StationList.getSelectedValue() + "'");
					rs.next();
					L_Station.setText(rs.getString("Station"));
					stmt.close();

				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		}
	}
	public class S_TimeListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent lse) {
			if (!lse.getValueIsAdjusting() && !S_TimeList.isSelectionEmpty()) {  // 현재 선택이 다 끝난 경우에 처리
				try {
					Statement stmt = conn.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT * FROM BUS_info WHERE S_Station IN(SELECT S_id FROM Station_info "
							+ "WHERE Station = '" +
							S_Station.getText() + "') AND L_Station IN(SELECT S_id FROM Station_info"
							+ " WHERE Station = '"+L_Station.getText()+"') AND S_Clock = '" +(String)S_TimeList.getSelectedValue() + "'");
					rs.next();
					BusNumber.setText(rs.getString("B_id"));
					S_Time.setText(rs.getString("S_clock"));
					L_Time.setText(rs.getString("L_clock"));



					stmt.close();
					TimeCheck=true;
					inquiryPanel.repaint();

				} catch (SQLException sqlex) {
					System.out.println("SQL 에러 : " + sqlex.getMessage());
					sqlex.printStackTrace();
				} catch (Exception ex) {
					System.out.println("DB Handling 에러(리스트 리스너) : " + ex.getMessage());
					ex.printStackTrace();
				}
			}

		}
	}
}