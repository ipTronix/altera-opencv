/*
 * GPIO interrupt handling example
 * Copyright (C) 2013  Altera
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

#include <linux/module.h>
#include <linux/irq.h>
#include <linux/interrupt.h>
#include <linux/kernel.h>
#include <linux/gpio.h>

static int gpio_number = -1;

irqreturn_t gpio_isr(int this_irq, void *dev_id)
{
	printk("Interrupt happened at gpio:%d\n", gpio_number);
	return IRQ_HANDLED;
}

static void __exit gpio_interrupt_exit (void)
{
	free_irq(gpio_to_irq(gpio_number), 0);
	return;
}

static int __init gpio_interrupt_init (void)
{
	int r;

	if (gpio_number < 0) {
		printk("Please specify a valid gpio_number\n");
		return -1;
	}

	int irq_number = gpio_to_irq(gpio_number);

	r = request_irq(irq_number, gpio_isr, IRQF_DISABLED, 0, 0);
	if (r) {
		printk("failure requesting irq %i\n", irq_number);
		return r;
	}
	return 0;
}

MODULE_LICENSE("GPL");

module_param(gpio_number, int, 0);

module_init(gpio_interrupt_init);
module_exit(gpio_interrupt_exit);
