package com.zatsepin.controller;

import com.zatsepin.entity.Counterparty;
import com.zatsepin.service.CounterpartyServiceImpl;
import com.zatsepin.util.CounterpartyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Реализует контроллер для отображения запрашиваемых URL на методы-обработчики.
 */
@Controller
@RequestMapping(value = "/counterparties")
public class CounterpartyController {

    private static Logger logger = LoggerFactory.getLogger(CounterpartyController.class);

    private CounterpartyServiceImpl counterpartyService;
    private CounterpartyValidator validator;

    /**
     * Посредством сервисного слоя возвращает список всех контрагентов
     *
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return имя представления для отображения списка всех контрагентов
     */
    @GetMapping
    public String list(Model uiModel) {
        logger.info("Listing counterparties");
        List<Counterparty> counterparties = counterpartyService.findAll();
        uiModel.addAttribute("counterparties", counterparties);
        logger.info("Number of counterparties: " + counterparties.size());
        return "counterparties";
    }

    /**
     * Посредством сервисного слоя выполняет поиск контрагента по идентификатору
     * переданному в качестве перменной пути GET-запроса.
     *
     * @param id идентификатор отображаемого контрагента
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return имя представления для отображения контрагента
     */
    @GetMapping(value = "/{id}")
    public String showById(@PathVariable("id") Long id, Model uiModel) {
        logger.info("Counterparty requested with id: " + id);
        Counterparty counterparty = counterpartyService.findById(id).get();
        uiModel.addAttribute("counterparty", counterparty);
        return "show";
    }

    /**
     * Возвращает представление содержащее форму для обновления контрагента.
     *
     * @param id идентификатор обновляемого контрагента
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return имя представления содержащего форму для обновления контрагента
     */
    @GetMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel) {
        logger.info("Requested update form for counterparty with id: " + id);
        uiModel.addAttribute("counterparty", counterpartyService.findById(id));
        return "update";
    }

    /**
     * Возвращает представление содержащее форму для создания контрагента.
     *
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return имя представления содержащего форму для создания контрагента
     */
    @GetMapping(value = "/new")
    public String createForm(Model uiModel) {
        logger.info("Requested create form for new counterparty");
        Counterparty counterparty = new Counterparty();
        uiModel.addAttribute("counterparty", counterparty);
        return "new";
    }

    /**
     * Посредством сервисного слоя выполняет сохранение контрагента.
     *
     * @param counterparty объект сохраняемого контрагента
     * @param bindingResult объект содержащий ошибки валидации объекта контрагента
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return перенаправление на указанный URL
     */
    @PostMapping
    public String create(@Valid Counterparty counterparty, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            logger.info("Counterparty has validation errors and not allowed to be created!");
            uiModel.addAttribute(counterparty);
            return "new";
        }
        if (validator.isInvalidName(counterparty.getName())
                || validator.isInvalidInn(counterparty.getInn())
                || validator.isInvalidKpp(counterparty.getKpp())
                || validator.isInvalidBic(counterparty.getBic())
                || validator.isInvalidAccountNumber(counterparty.getAccountNumber(), counterparty.getBic())) {
            uiModel.addAttribute("isInvalidName", validator.isInvalidName(counterparty.getName()));
            uiModel.addAttribute("isInvalidInn", validator.isInvalidInn(counterparty.getInn()));
            uiModel.addAttribute("isInvalidKpp", validator.isInvalidKpp(counterparty.getKpp()));
            uiModel.addAttribute("isInvalidBic", validator.isInvalidBic(counterparty.getBic()));
            uiModel.addAttribute("isInvalidAccountNumber", validator.isInvalidAccountNumber(counterparty.getAccountNumber(), counterparty.getBic()));
            return "new";
        }
        counterpartyService.save(counterparty);
        logger.info("Counterparty created with id: " + counterparty.getId());
        return "redirect:/counterparties/" + counterparty.getId();
    }

    /**
     * Посредством сервисного слоя выполняет обновление контрагента.
     *
     * @param counterparty объект обновляемого контрагента
     * @param bindingResult объект содержащий ошибки валидации объекта контрагента
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @return перенаправление на указанный URL
     */
    @PatchMapping(value = "/{id}")
    public String update(@Valid Counterparty counterparty, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            logger.info("Counterparty has validation errors and not allowed to be updated!");
            uiModel.addAttribute(counterparty);
            return "update";
        }
        if (validator.isInvalidInn(counterparty.getInn())
                || validator.isInvalidKpp(counterparty.getKpp())
                || validator.isInvalidBic(counterparty.getBic())
                || validator.isInvalidAccountNumber(counterparty.getAccountNumber(), counterparty.getBic())) {
            uiModel.addAttribute("isInvalidInn", validator.isInvalidInn(counterparty.getInn()));
            uiModel.addAttribute("isInvalidKpp", validator.isInvalidKpp(counterparty.getKpp()));
            uiModel.addAttribute("isInvalidBic", validator.isInvalidBic(counterparty.getBic()));
            uiModel.addAttribute("isInvalidAccountNumber", validator.isInvalidAccountNumber(counterparty.getAccountNumber(), counterparty.getBic()));
            return "update";
        }
        counterpartyService.save(counterparty);
        logger.info("Counterparty updated with id: " + counterparty.getId());
        return "redirect:/counterparties/" + counterparty.getId();
    }

    /**
     * Посредством сервисного слоя выполняет поиск контрагента по наименованию
     * переданному в качестве параметра GET-запроса.
     *
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @param request объект HTTP запроса
     * @return имя представления для отображения контрагента
     */
    @GetMapping(value = "/name")
    public String findByName(Model uiModel, HttpServletRequest request) {
        String name = request.getParameter("name");
        logger.info("Requested counterparty with name: " + name);
        Counterparty counterparty = counterpartyService.findByName(name);
        if (counterparty == null) {
            return "nothingfind";
        }
        uiModel.addAttribute("counterparty", counterparty);
        return "show";
    }

    /**
     * Посредством сервисного слоя выполняет поиск контрагента по номеру счёта и БИК
     * переданным в качестве параметров GET-запроса.
     *
     * @param uiModel объект модели для обмена данными между контроллером и представлением
     * @param request объект HTTP запроса
     * @return имя представления для отображения контрагента
     */
    @GetMapping(value = "/accountandbic")
    public String findByAccountNumberAndBic(Model uiModel, HttpServletRequest request) {
        String accountNumber = request.getParameter("accountNumber");
        String bic = request.getParameter("bic");
        logger.info("Requested counterparty with account number: " + accountNumber + " and BIC: " + bic);
        Counterparty counterparty = counterpartyService.findByAccountNumberAndBic(accountNumber, bic);
        if (counterparty == null) {
            return "nothingfind";
        }
        uiModel.addAttribute("counterparty", counterparty);
        return "show";
    }

    /**
     * Посредством сервисного слоя выполняет удаление контрагента.
     *
     * @param id идентификатор удаляемого контрагента
     * @return перенаправление на указанный URL
     */
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        logger.info("Counterparty requested to be deleted with id: " + id);
        counterpartyService.delete(id);
        return "redirect:/counterparties";
    }

    @Autowired
    public void setCounterpartyService(CounterpartyServiceImpl counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @Autowired
    public void setValidator(CounterpartyValidator validator) {
        this.validator = validator;
    }
}
