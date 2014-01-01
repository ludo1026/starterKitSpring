package org.telosys.starterkits.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.service.AuthorService;
import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.bean.Badge;
import org.telosys.starterkits.bean.Book;
import org.telosys.starterkits.bean.BookOrder;
import org.telosys.starterkits.bean.BookOrderItem;
import org.telosys.starterkits.bean.BookOrderItemId;
import org.telosys.starterkits.bean.Country;
import org.telosys.starterkits.bean.Customer;
import org.telosys.starterkits.bean.Employee;
import org.telosys.starterkits.bean.EmployeeGroup;
import org.telosys.starterkits.bean.EmployeeGroupId;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.bean.Review;
import org.telosys.starterkits.bean.ReviewId;
import org.telosys.starterkits.bean.Shop;
import org.telosys.starterkits.bean.Synopsis;
import org.telosys.starterkits.bean.Workgroup;
import org.telosys.starterkits.service.AuthorService;
import org.telosys.starterkits.service.BadgeService;
import org.telosys.starterkits.service.BookService;
import org.telosys.starterkits.service.BookOrderService;
import org.telosys.starterkits.service.BookOrderItemService;
import org.telosys.starterkits.service.CountryService;
import org.telosys.starterkits.service.CustomerService;
import org.telosys.starterkits.service.EmployeeService;
import org.telosys.starterkits.service.EmployeeGroupService;
import org.telosys.starterkits.service.PublisherService;
import org.telosys.starterkits.service.ReviewService;
import org.telosys.starterkits.service.ShopService;
import org.telosys.starterkits.service.SynopsisService;
import org.telosys.starterkits.service.WorkgroupService;

@Component
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
    
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installTypeConverters(getObject());
        installLabelConverters(getObject());
    }
	
	protected void installTypeConverters(FormatterRegistry registry) {
		registry.addConverter(getStringToStringConverter());
		registry.addConverter(getStringToDateConverter());
		registry.addConverter(getDateToStringConverter());
	}
    
	public Converter<String, String> getStringToStringConverter() {
        return new Converter<String, String>() {
 
            @Override
            public String convert(String source) {
                return StringUtils.trimToNull(source);
            }
        };
    }

	public Converter<String, Date> getStringToDateConverter() {
        return new Converter<String, Date>() {
 
            @Override
            public Date convert(String source) {
                try {
                    return sdf.parse(source);
                } catch (ParseException e) {
                    return null;
                }
            }
        };
    }

	public Converter<Date, String> getDateToStringConverter() {
        return new Converter<Date, String>() {
 
            @Override
            public String convert(Date source) {
                if(source == null) {
                	return null;
                } else {
                    return sdf.format(source);
                }
            }
        };
    }
	
    public void installLabelConverters(FormatterRegistry registry) {
		// Author
        registry.addConverter(getAuthorToStringConverter());
        registry.addConverter(getIdToAuthorConverter());
        registry.addConverter(getStringToAuthorConverter());
		// Badge
        registry.addConverter(getBadgeToStringConverter());
        registry.addConverter(getIdToBadgeConverter());
        registry.addConverter(getStringToBadgeConverter());
		// Book
        registry.addConverter(getBookToStringConverter());
        registry.addConverter(getIdToBookConverter());
        registry.addConverter(getStringToBookConverter());
		// BookOrder
        registry.addConverter(getBookOrderToStringConverter());
        registry.addConverter(getIdToBookOrderConverter());
        registry.addConverter(getStringToBookOrderConverter());
		// BookOrderItem
        registry.addConverter(getBookOrderItemToStringConverter());
        registry.addConverter(getIdToBookOrderItemConverter());
        registry.addConverter(getStringToBookOrderItemConverter());
		// Country
        registry.addConverter(getCountryToStringConverter());
        registry.addConverter(getIdToCountryConverter());
		// Customer
        registry.addConverter(getCustomerToStringConverter());
        registry.addConverter(getIdToCustomerConverter());
		// Employee
        registry.addConverter(getEmployeeToStringConverter());
        registry.addConverter(getIdToEmployeeConverter());
		// EmployeeGroup
        registry.addConverter(getEmployeeGroupToStringConverter());
        registry.addConverter(getIdToEmployeeGroupConverter());
        registry.addConverter(getStringToEmployeeGroupConverter());
		// Publisher
        registry.addConverter(getPublisherToStringConverter());
        registry.addConverter(getIdToPublisherConverter());
        registry.addConverter(getStringToPublisherConverter());
		// Review
        registry.addConverter(getReviewToStringConverter());
        registry.addConverter(getIdToReviewConverter());
        registry.addConverter(getStringToReviewConverter());
		// Shop
        registry.addConverter(getShopToStringConverter());
        registry.addConverter(getIdToShopConverter());
		// Synopsis
        registry.addConverter(getSynopsisToStringConverter());
        registry.addConverter(getIdToSynopsisConverter());
        registry.addConverter(getStringToSynopsisConverter());
		// Workgroup
        registry.addConverter(getWorkgroupToStringConverter());
        registry.addConverter(getIdToWorkgroupConverter());
        registry.addConverter(getStringToWorkgroupConverter());
    }
    
	@Resource
	private AuthorService authorService;

    public Converter<Author, String> getAuthorToStringConverter() {
        return new Converter<Author, java.lang.String>() {
            public String convert(Author Author) {
				return new StringBuilder().append(Author.getId()).toString();
            }
        };
    }
    
    public Converter<String, Author> getStringToAuthorConverter() {
        return new Converter<java.lang.String, Author>() {
            public Author convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Author.class);
            }
        };
    }
    
    public Converter<Integer, Author> getIdToAuthorConverter() {
        return new Converter<Integer, Author>() {
            public Author convert(Integer id) {
                return authorService.load(id);
            }
        };
    }

	@Resource
	private BadgeService badgeService;

    public Converter<Badge, String> getBadgeToStringConverter() {
        return new Converter<Badge, java.lang.String>() {
            public String convert(Badge Badge) {
				return new StringBuilder().append(Badge.getBadgeNumber()).toString();
            }
        };
    }
    
    public Converter<String, Badge> getStringToBadgeConverter() {
        return new Converter<java.lang.String, Badge>() {
            public Badge convert(String badgeNumber) {
                return getObject().convert(getObject().convert(badgeNumber, Integer.class), Badge.class);
            }
        };
    }
    
    public Converter<Integer, Badge> getIdToBadgeConverter() {
        return new Converter<Integer, Badge>() {
            public Badge convert(Integer badgeNumber) {
                return badgeService.load(badgeNumber);
            }
        };
    }

	@Resource
	private BookService bookService;

    public Converter<Book, String> getBookToStringConverter() {
        return new Converter<Book, java.lang.String>() {
            public String convert(Book Book) {
				return new StringBuilder().append(Book.getId()).toString();
            }
        };
    }
    
    public Converter<String, Book> getStringToBookConverter() {
        return new Converter<java.lang.String, Book>() {
            public Book convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Book.class);
            }
        };
    }
    
    public Converter<Integer, Book> getIdToBookConverter() {
        return new Converter<Integer, Book>() {
            public Book convert(Integer id) {
                return bookService.load(id);
            }
        };
    }

	@Resource
	private BookOrderService bookorderService;

    public Converter<BookOrder, String> getBookOrderToStringConverter() {
        return new Converter<BookOrder, java.lang.String>() {
            public String convert(BookOrder BookOrder) {
				return new StringBuilder().append(BookOrder.getId()).toString();
            }
        };
    }
    
    public Converter<String, BookOrder> getStringToBookOrderConverter() {
        return new Converter<java.lang.String, BookOrder>() {
            public BookOrder convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), BookOrder.class);
            }
        };
    }
    
    public Converter<Integer, BookOrder> getIdToBookOrderConverter() {
        return new Converter<Integer, BookOrder>() {
            public BookOrder convert(Integer id) {
                return bookorderService.load(id);
            }
        };
    }

	@Resource
	private BookOrderItemService bookorderitemService;

    public Converter<BookOrderItem, String> getBookOrderItemToStringConverter() {
        return new Converter<BookOrderItem, java.lang.String>() {
            public String convert(BookOrderItem BookOrderItem) {
				return new StringBuilder().append(BookOrderItem.getId()).toString();
            }
        };
    }
    
    public Converter<String, BookOrderItem> getStringToBookOrderItemConverter() {
        return new Converter<java.lang.String, BookOrderItem>() {
            public BookOrderItem convert(String id) {
                return getObject().convert(getObject().convert(id, BookOrderItemId.class), BookOrderItem.class);
            }
        };
    }
    
    public Converter<BookOrderItemId, BookOrderItem> getIdToBookOrderItemConverter() {
        return new Converter<BookOrderItemId, BookOrderItem>() {
            public BookOrderItem convert(BookOrderItemId id) {
                return bookorderitemService.load(id);
            }
        };
    }

	@Resource
	private CountryService countryService;

    public Converter<Country, String> getCountryToStringConverter() {
        return new Converter<Country, java.lang.String>() {
            public String convert(Country Country) {
				return new StringBuilder().append(Country.getCode()).toString();
            }
        };
    }
    
    public Converter<String, Country> getIdToCountryConverter() {
        return new Converter<String, Country>() {
            public Country convert(String code) {
                return countryService.load(code);
            }
        };
    }

	@Resource
	private CustomerService customerService;

    public Converter<Customer, String> getCustomerToStringConverter() {
        return new Converter<Customer, java.lang.String>() {
            public String convert(Customer Customer) {
				return new StringBuilder().append(Customer.getCode()).toString();
            }
        };
    }
    
    public Converter<String, Customer> getIdToCustomerConverter() {
        return new Converter<String, Customer>() {
            public Customer convert(String code) {
                return customerService.load(code);
            }
        };
    }

	@Resource
	private EmployeeService employeeService;

    public Converter<Employee, String> getEmployeeToStringConverter() {
        return new Converter<Employee, java.lang.String>() {
            public String convert(Employee Employee) {
				return new StringBuilder().append(Employee.getCode()).toString();
            }
        };
    }
    
    public Converter<String, Employee> getIdToEmployeeConverter() {
        return new Converter<String, Employee>() {
            public Employee convert(String code) {
                return employeeService.load(code);
            }
        };
    }

	@Resource
	private EmployeeGroupService employeegroupService;

    public Converter<EmployeeGroup, String> getEmployeeGroupToStringConverter() {
        return new Converter<EmployeeGroup, java.lang.String>() {
            public String convert(EmployeeGroup EmployeeGroup) {
				return new StringBuilder().append(EmployeeGroup.getId()).toString();
            }
        };
    }
    
    public Converter<String, EmployeeGroup> getStringToEmployeeGroupConverter() {
        return new Converter<java.lang.String, EmployeeGroup>() {
            public EmployeeGroup convert(String id) {
                return getObject().convert(getObject().convert(id, EmployeeGroupId.class), EmployeeGroup.class);
            }
        };
    }
    
    public Converter<EmployeeGroupId, EmployeeGroup> getIdToEmployeeGroupConverter() {
        return new Converter<EmployeeGroupId, EmployeeGroup>() {
            public EmployeeGroup convert(EmployeeGroupId id) {
                return employeegroupService.load(id);
            }
        };
    }

	@Resource
	private PublisherService publisherService;

    public Converter<Publisher, String> getPublisherToStringConverter() {
        return new Converter<Publisher, java.lang.String>() {
            public String convert(Publisher Publisher) {
				return new StringBuilder().append(Publisher.getCode()).toString();
            }
        };
    }
    
    public Converter<String, Publisher> getStringToPublisherConverter() {
        return new Converter<java.lang.String, Publisher>() {
            public Publisher convert(String code) {
                return getObject().convert(getObject().convert(code, Integer.class), Publisher.class);
            }
        };
    }
    
    public Converter<Integer, Publisher> getIdToPublisherConverter() {
        return new Converter<Integer, Publisher>() {
            public Publisher convert(Integer code) {
                return publisherService.load(code);
            }
        };
    }

	@Resource
	private ReviewService reviewService;

    public Converter<Review, String> getReviewToStringConverter() {
        return new Converter<Review, java.lang.String>() {
            public String convert(Review Review) {
				return new StringBuilder().append(Review.getId()).toString();
            }
        };
    }
    
    public Converter<String, Review> getStringToReviewConverter() {
        return new Converter<java.lang.String, Review>() {
            public Review convert(String id) {
                return getObject().convert(getObject().convert(id, ReviewId.class), Review.class);
            }
        };
    }
    
    public Converter<ReviewId, Review> getIdToReviewConverter() {
        return new Converter<ReviewId, Review>() {
            public Review convert(ReviewId id) {
                return reviewService.load(id);
            }
        };
    }

	@Resource
	private ShopService shopService;

    public Converter<Shop, String> getShopToStringConverter() {
        return new Converter<Shop, java.lang.String>() {
            public String convert(Shop Shop) {
				return new StringBuilder().append(Shop.getCode()).toString();
            }
        };
    }
    
    public Converter<String, Shop> getIdToShopConverter() {
        return new Converter<String, Shop>() {
            public Shop convert(String code) {
                return shopService.load(code);
            }
        };
    }

	@Resource
	private SynopsisService synopsisService;

    public Converter<Synopsis, String> getSynopsisToStringConverter() {
        return new Converter<Synopsis, java.lang.String>() {
            public String convert(Synopsis Synopsis) {
				return new StringBuilder().append(Synopsis.getBookId()).toString();
            }
        };
    }
    
    public Converter<String, Synopsis> getStringToSynopsisConverter() {
        return new Converter<java.lang.String, Synopsis>() {
            public Synopsis convert(String bookId) {
                return getObject().convert(getObject().convert(bookId, Integer.class), Synopsis.class);
            }
        };
    }
    
    public Converter<Integer, Synopsis> getIdToSynopsisConverter() {
        return new Converter<Integer, Synopsis>() {
            public Synopsis convert(Integer bookId) {
                return synopsisService.load(bookId);
            }
        };
    }

	@Resource
	private WorkgroupService workgroupService;

    public Converter<Workgroup, String> getWorkgroupToStringConverter() {
        return new Converter<Workgroup, java.lang.String>() {
            public String convert(Workgroup Workgroup) {
				return new StringBuilder().append(Workgroup.getId()).toString();
            }
        };
    }
    
    public Converter<String, Workgroup> getStringToWorkgroupConverter() {
        return new Converter<java.lang.String, Workgroup>() {
            public Workgroup convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), Workgroup.class);
            }
        };
    }
    
    public Converter<Short, Workgroup> getIdToWorkgroupConverter() {
        return new Converter<Short, Workgroup>() {
            public Workgroup convert(Short id) {
                return workgroupService.load(id);
            }
        };
    }

}
