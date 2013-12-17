package org.telosys.starterkits.web;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;
import org.telosys.starterkits.bean.Author;
import org.telosys.starterkits.bean.Publisher;
import org.telosys.starterkits.service.AuthorService;
import org.telosys.starterkits.service.PublisherService;

@Component
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	
	@Resource
	private PublisherService publisherService;

    public Converter<Publisher, String> getPublisherToStringConverter() {
        return new Converter<Publisher, java.lang.String>() {
            public String convert(Publisher Publisher) {
                return new StringBuilder().append(Publisher.getName()).toString();
            }
        };
    }
    
    public Converter<Integer, Publisher> getIdToPublisherConverter() {
        return new Converter<Integer, Publisher>() {
            public Publisher convert(Integer id) {
                return publisherService.load(id);
            }
        };
    }
    
    public Converter<String, Publisher> getStringToPublisherConverter() {
        return new Converter<java.lang.String, Publisher>() {
            public Publisher convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Publisher.class);
            }
        };
    }
    
    @Resource
    private AuthorService AuthorService;
    
    public Converter<Author, String> getAuthorToStringConverter() {
    	return new Converter<Author, java.lang.String>() {
    		public String convert(Author author) {
    			return new StringBuilder().append(author.getFirstName()+" "+author.getLastName()).toString();
    		}
    	};
    }
    
    public Converter<Integer, Author> getIdToAuthorConverter() {
    	return new Converter<Integer, Author>() {
    		public Author convert(Integer id) {
    			return AuthorService.load(id);
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

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getPublisherToStringConverter());
        registry.addConverter(getIdToPublisherConverter());
        registry.addConverter(getStringToPublisherConverter());
        registry.addConverter(getAuthorToStringConverter());
        registry.addConverter(getIdToAuthorConverter());
        registry.addConverter(getStringToAuthorConverter());
    }
    
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
