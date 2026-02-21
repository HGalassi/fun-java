package fun.config.startupapp;

import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.ai.converter.FormatProvider;

public interface StructuredOutputConverter<T> extends Converter<String, T>, FormatProvider {}
