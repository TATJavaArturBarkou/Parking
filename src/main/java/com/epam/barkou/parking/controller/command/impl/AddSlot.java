package main.java.com.epam.barkou.parking.controller.command.impl;

import main.java.com.epam.barkou.parking.bean.Slot;
import main.java.com.epam.barkou.parking.controller.command.Command;
import main.java.com.epam.barkou.parking.controller.util.RequestChecker;
import main.java.com.epam.barkou.parking.service.ISlotService;
import main.java.com.epam.barkou.parking.service.exception.ServiceException;
import main.java.com.epam.barkou.parking.service.factory.ServiceFactory;
import main.java.com.epam.barkou.parking.view.scanner.UserInput;
import org.apache.log4j.Logger;

public class AddSlot extends Command {

    private final static Logger log = Logger.getLogger(UserInput.class);
    private final int slotSizeIndex = 1;
    private final int slotCoveredIndex = 2;
    private String response = null;
    private final static String MESSAGE_SLOT_ADDED = "Slot has been added successfully";
    private final static String MESSAGE_SLOT_NOT_ADDED = "Unable to add Slot";



    @Override
    public String execute(String request) {

        String[] requestData = request.split(SPLITTER);

        int paramsQuantity = 2;
        if (RequestChecker.checkParamsQuantity(requestData, paramsQuantity)) {


            String sizeInUpperCase = requestData[slotSizeIndex].toUpperCase();
            Slot.Size size = Slot.Size.valueOf(sizeInUpperCase);
            boolean covered = Boolean.parseBoolean(requestData[slotCoveredIndex]);

            Slot slot = new Slot(size, covered);

            ServiceFactory factory = ServiceFactory.getInstance();
            ISlotService slotService = factory.getSlotService();

            try {

                slotService.addSlot(slot);
                response = MESSAGE_SLOT_ADDED;

            } catch (ServiceException e) {
                response = MESSAGE_SLOT_NOT_ADDED;
                log.error(e);

            }

        } else {
            response = MESSAGE_NOT_ENOUGH_PARAMS;
        }
        return response;
    }

}
