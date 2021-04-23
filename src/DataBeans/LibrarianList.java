package DataBeans;
import java.util.Date;

public class LibrarianList {
    private String periodicalName;
    private String currentReader;
    private String statusCur;
    private Date expirationDateCurrent;
    private String nextReader;
    private String statusNext;
    private Date expirationDateNext;
    private String availability;

    public String 	getPeriodicalName() 		{ return periodicalName; 		}
    public String 	getCurrentReader() 			{ return currentReader; 		}
    public String 	getStatusCur() 				{ return statusCur; 			}
    public Date 	getExpirationDateCurrent()	{ return expirationDateCurrent;	}
    public String 	getNextReader() 			{ return nextReader; 			}
    public String 	getStatusNext() 			{ return statusNext; 			}
    public Date 	getExpirationDateNext()		{ return expirationDateNext;	}
    public String	getAvailability()			{ return availability;		}
    
    public void setPeriodicalName(String s)  	{ periodicalName = s;    	}
    public void setCurrentReader(String s)  	{ currentReader = s;    	}
    public void setStatusCur(String s)			{ statusCur = s;			}
    public void setExpirationDateCurrent(Date d){ expirationDateCurrent = d;}
    public void setNextReader(String s)  		{ nextReader = s;    		}
    public void setStatusNext(String s)			{ statusNext = s;			}
    public void setExpirationDateNext(Date d)	{ expirationDateNext = d;	}
    public void setAvailability(String s)		{ availability = s;			}
}	