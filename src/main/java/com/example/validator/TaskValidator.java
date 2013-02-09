package com.example.validator;

import com.generated.tasks.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("taskValidator")
public class TaskValidator implements Validator
{
    @SuppressWarnings("unchecked")
    @Override
    public boolean supports(Class clazz)
    {
        return Task.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object model, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "what","required.what", "What is required.");
    }
}
