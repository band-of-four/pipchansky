import javax.faces.application.FacesMessage
import javax.faces.component.UIComponent
import javax.faces.context.FacesContext
import javax.faces.validator.FacesValidator
import javax.faces.validator.Validator
import javax.faces.validator.ValidatorException

@FacesValidator("yValidator")
class YValidator : Validator {

  override fun validate(context: FacesContext?, component: UIComponent?, value: Any?) {
    when {
      value == null -> throw ValidatorException(FacesMessage("Это обязательное поле!"))
      value !is Double -> throw ValidatorException(FacesMessage("Введите ЧИСЛО!"))
      value <= -3 || value >= 5 -> throw ValidatorException(FacesMessage("Число от -3 до 5!"))
    }
  }

}
