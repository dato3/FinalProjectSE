package DataBeans;
import java.util.Date;

public class Subscription {
	private String userName;
    private String periodicalName;
    private Date dateSubscribed;
    private Date expirationDate;
    private String availability;
    private String status;

    public String 	getUserName() 		{ return userName; 			}
    public String 	getPeriodicalName() { return periodicalName; 	}
    public Date 	getDateSubscribed()	{ return dateSubscribed;	}
    public Date 	getExpirationDate()	{ return expirationDate;	}
    public String	getAvailability()	{ return availability;		}
    public String	getStatus()			{ return status;			}
    
    public void setUserName(String s)  			{ userName = s;    			}
    public void setPeriodicalName(String s)  	{ periodicalName = s;    	}
    public void setDateSubscribed(Date d)		{ dateSubscribed = d;		}
    public void setExpirationDate(Date d)		{ expirationDate = d;		}
    public void setAvailability(String s)		{ availability = s;			}
    public void setStatus(String s)				{ status = s;				}
}	